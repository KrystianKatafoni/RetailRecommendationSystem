package krystiankatafoni.retailrecommendationsystem.dao.relationships;

import krystiankatafoni.retailrecommendationsystem.dao.DatabaseConnection;
import krystiankatafoni.retailrecommendationsystem.domain.relationships.CategoryWithProduct;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("categoryProduct")
public class CategoryProductRelationshipDao implements RelationshipDao<CategoryWithProduct> {

    private final String CREATE_RELATIONSHIPS = "USING PERIODIC COMMIT 500 LOAD CSV WITH HEADERS " +
            "FROM 'file:///products.csv' AS line " +
            "MATCH (p:Product),(c:Category) " +
            "WHERE p.id=line.id AND c.id=line.category_id" +
            " CREATE (p)-[pc:BELONG_TO]->(c)";

    @Override
    public void addRelationship(CategoryWithProduct categoryWithProduct) {

    }

    /**
     * Load all relationships for products from products.csv file and save to db.
     */
    @Override
    public void loadCollection() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            session.run(CREATE_RELATIONSHIPS);
        }
    }
}
