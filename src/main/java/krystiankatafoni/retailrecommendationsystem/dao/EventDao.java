package krystiankatafoni.retailrecommendationsystem.dao;

import krystiankatafoni.retailrecommendationsystem.domain.Event;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("event")
public class EventDao implements RelationshipDao<Event> {
    private final String CREATE_RELATIONSHIPS = "USING PERIODIC COMMIT 1000 LOAD CSV WITH HEADERS " +
            "FROM 'file:///events.csv' AS line " +
            "MATCH (p:Product),(u:User) " +
            "WHERE p.id=line.product_id AND u.id=line.user_id" +
            " CALL apoc.create.relationship(u, line.event,{}, p) YIELD rel REMOVE rel.noOp";
    @Override
    public void add(Event event) {}

    @Override
    public void loadAllRelationships() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            session.run(CREATE_RELATIONSHIPS);

        }
    }

}
