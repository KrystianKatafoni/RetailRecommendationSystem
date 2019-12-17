package krystiankatafoni.retailrecommendationsystem.dao;

import krystiankatafoni.retailrecommendationsystem.domain.Product;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("product")
public class ProductDao implements SingleNodeDao<Product> {

    private final String LOAD_PRODUCTS = "USING PERIODIC COMMIT 500 LOAD CSV WITH HEADERS FROM 'file:///products.csv' AS line " +
            "CREATE (p:Product { id: line.id, desc: line.product})";
    private final String CREATE_RELATIONSHIPS = "USING PERIODIC COMMIT 500 LOAD CSV WITH HEADERS " +
            "FROM 'file:///products.csv' AS line " +
            "MATCH (p:Product),(c:Category),(b:Brand) " +
            "WHERE p.id=line.id AND c.id=line.category_id AND b.id=line.brand_id" +
            " CREATE (b)-[bp:MANUFACTURED]->(p), (p)-[pc:BELONG_TO]->(c)";
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

    @Override
    public void loadAllFromCsv() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            session.run(LOAD_PRODUCTS);
            session.run(CREATE_RELATIONSHIPS);
            System.out.println("\nData loaded\n");
        }
    }
}
