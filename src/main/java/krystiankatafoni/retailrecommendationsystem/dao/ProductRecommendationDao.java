package krystiankatafoni.retailrecommendationsystem.dao;

import krystiankatafoni.retailrecommendationsystem.dao.generic.RecommendationDao;
import krystiankatafoni.retailrecommendationsystem.domain.ProductWithWeight;
import krystiankatafoni.retailrecommendationsystem.domain.nodes.Product;
import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
@Repository
public class ProductRecommendationDao implements RecommendationDao<ProductWithWeight,Integer> {
    private final String GET_RECOMMENDATIONS = "MATCH (p:Product {id: $id})-[:BELONG_TO]->(c:Category)<-[:BELONG_TO]-" +
            "(z:Product)<-[r:view |:cart|:purchased]-(u:User) WITH z as product, sum(toInteger(r.weight)) as weightSum" +
            " return product, weightSum order by weightSum desc limit $limit";

    /**
     *
     * @param id id of the product for recommendation
     * @param limit limit for the results
     * @return
     */
    @Override
    public Set<ProductWithWeight> getRecommendationsWithLimit(Integer id, Integer limit) {
        Set<ProductWithWeight> products = new HashSet<>();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id.toString());
        params.put("limit", limit);
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
                Session session = driver.session()) {
            StatementResult result = session.run(GET_RECOMMENDATIONS, params);
            while (result.hasNext()) {
                Record record = result.next();
                products.add(new ProductWithWeight(new Product(record.get("product").asNode().get("id").asString(),
                        record.get("product").asNode().get("desc").asString()),
                        record.get("weightSum").asInt()));
            }
        }
        return products;
    }
}
