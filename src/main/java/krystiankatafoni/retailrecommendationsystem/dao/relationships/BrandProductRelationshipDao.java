package krystiankatafoni.retailrecommendationsystem.dao.relationships;

import krystiankatafoni.retailrecommendationsystem.dao.DatabaseConnection;
import krystiankatafoni.retailrecommendationsystem.domain.relationships.BrandWithProduct;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("brandProduct")
public class BrandProductRelationshipDao implements RelationshipDao<BrandWithProduct> {

    private final String CREATE_RELATIONSHIPS = "USING PERIODIC COMMIT 500 LOAD CSV WITH HEADERS " +
            "FROM 'file:///products.csv' AS line " +
            "MATCH (p:Product),(b:Brand) " +
            "WHERE p.id=line.id AND b.id=line.brand_id" +
            " CREATE (b)-[bp:MANUFACTURED]->(p)";

    @Override
    public void addRelationship(BrandWithProduct brandWithProduct) {

    }

    @Override
    public void loadCollection() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            session.run(CREATE_RELATIONSHIPS);
        }
    }
}
