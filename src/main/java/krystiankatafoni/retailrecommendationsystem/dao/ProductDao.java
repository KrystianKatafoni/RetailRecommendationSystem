package krystiankatafoni.retailrecommendationsystem.dao;

import krystiankatafoni.retailrecommendationsystem.domain.Product;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Dao which represents operation on products in db
 */
@Repository
@Qualifier("product")
public class ProductDao implements NodeWithRelationshipDao<Product> {

    private final String LOAD_PRODUCTS = "USING PERIODIC COMMIT 500 LOAD CSV WITH HEADERS FROM 'file:///products.csv' AS line " +
            "CREATE (p:Product { id: line.id, desc: line.product})";

    private final String CREATE_RELATIONSHIPS = "USING PERIODIC COMMIT 500 LOAD CSV WITH HEADERS " +
            "FROM 'file:///products.csv' AS line " +
            "MATCH (p:Product),(c:Category),(b:Brand) " +
            "WHERE p.id=line.id AND c.id=line.category_id AND b.id=line.brand_id" +
            " CREATE (b)-[bp:MANUFACTURED]->(p), (p)-[pc:BELONG_TO]->(c)";

    private final String GET_RECOMMENDATIONS = "MATCH (p:Product {id: $id})-[:BELONG_TO]->(c:Category)<-[:BELONG_TO]-" +
            "(z:Product)<-[r:view |:cart|:purchased]-(u:User) WITH z as product, sum(toInteger(r.weight)) as weightSum" +
            " return product, weightSum order by weightSum desc limit $limit";
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
    public void loadAllNodes() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
                Session session = driver.session()) {
            session.run(LOAD_PRODUCTS);

        }
    }

    @Override
    public void add(Product product) {

    }

    /**
     * Load all relationships for products from products.csv file and save to db.
     */
    @Override
    public void loadAllRelationships() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
                Session session = driver.session()) {
            session.run(CREATE_RELATIONSHIPS);
        }
    }

    /**
     *
     * @param id id of the product for recommendation
     * @param limit limit for the results
     * @return collection of recommended products
     */
    @Override
    public Set<Product> getRecommendationsWithLimit(Integer id, Integer limit) {
        Set<Product> products = new HashSet<>();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id.toString());
        params.put("limit", limit);
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
                Session session = driver.session()) {
            StatementResult result = session.run(GET_RECOMMENDATIONS, params);
            System.out.println("Ala");
            while (result.hasNext()) {
                Record record = result.next();
                products.add(new Product(record.get("product").asNode().get("id").asString(),
                        record.get("product").asNode().get("desc").asString(),
                        record.get("weightSum").asInt()));
            }
        }
        return products;
    }
}
