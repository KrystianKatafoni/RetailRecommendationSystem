package krystiankatafoni.retailrecommendationsystem.dao;

import krystiankatafoni.retailrecommendationsystem.dao.generic.FullTextSearchDao;
import krystiankatafoni.retailrecommendationsystem.domain.ProductWithSearchScore;
import krystiankatafoni.retailrecommendationsystem.domain.nodes.Product;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.exceptions.ClientException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class ProductFullTextSearchDao implements FullTextSearchDao<ProductWithSearchScore> {
    private final static String INDEX_NAME = "productDescriptions";
    private final static String CREATE_FULL_TEXT_INDEX = "CALL db.index.fulltext." +
            "createNodeIndex($indexName,[$label],[$property])";
    private final static String DROP_INDEX = "CALL db.index.fulltext.drop($indexName)";
    private final static String FIND_PHRASE = "CALL db.index.fulltext.queryNodes($indexName, $phrase) " +
            "YIELD node, score where score > $probability RETURN node, score";
    @Override
    public void createIndex() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("indexName", INDEX_NAME);
            params.put("label", Product.label);
            params.put("property", "desc");
            session.run(CREATE_FULL_TEXT_INDEX, params);
        }
    }

    @Override
    public void dropIndex() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("indexName", INDEX_NAME);
            session.run(DROP_INDEX, params);
        }catch (ClientException ex) {
            System.out.println("Cannot drop index, not exist");
        }
    }

    @Override
    public Set<ProductWithSearchScore> getNodesWithPhrase(String phrase, double minProbability) {
        Set<ProductWithSearchScore> productsWithSearchScore = new HashSet<>();

        Map<String, Object> params = new HashMap<>();
        params.put("phrase", phrase);
        params.put("indexName", INDEX_NAME);
        params.put("probability", minProbability);
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            StatementResult result = session.run(FIND_PHRASE, params);
            while (result.hasNext()) {
                Record record = result.next();
                ProductWithSearchScore productWithSearchScore = new ProductWithSearchScore(
                        new Product(record.get("node").asNode().get("id").asString(),
                                record.get("node").asNode().get("desc").asString()),record.get("score").asDouble());
                productsWithSearchScore.add(productWithSearchScore);
            }
        }
        return productsWithSearchScore;
    }
}
