package krystiankatafoni.retailrecommendationsystem.dao.nodes;

import krystiankatafoni.retailrecommendationsystem.dao.DatabaseConnection;
import krystiankatafoni.retailrecommendationsystem.dao.generic.NodeDao;
import krystiankatafoni.retailrecommendationsystem.domain.nodes.Product;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Dao which represents operation on products in db
 */
@Repository
@Qualifier("product")
public class ProductDao implements NodeDao<Product> {

    private final String LOAD_PRODUCTS = "USING PERIODIC COMMIT 500 LOAD CSV WITH HEADERS FROM " +
            "'file:///products.csv' AS line " +
            "CREATE (p:Product { id: line.id, desc: line.product})";
    private final String CREATE_FULL_TEXT_INDEX = "CALL db.index.fulltext.createNodeIndex(\"productDescriptions\",[\"Product\"],[\"desc\"])";
    private final String FIND_PHRASE = "CALL db.index.fulltext.queryNodes(\"produtDescriptions\", $phrase) YIELD node, score where score > $probability RETURN node.desc, score";

    @Override
    public Optional<Product> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    /**
     * Load all nodes from products.csv file and save to db. Method is using query saved
     * in LOAD_PRODUCTS variable
     */
    @Override
    public void loadCollection() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
                Session session = driver.session()) {
            session.run(LOAD_PRODUCTS);

        }
    }

}
