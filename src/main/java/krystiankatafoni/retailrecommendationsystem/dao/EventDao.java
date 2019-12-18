package krystiankatafoni.retailrecommendationsystem.dao;

import krystiankatafoni.retailrecommendationsystem.domain.Event;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Dao which represents operations on event in db(relationship beetwen product and user).
 * Event is a action which take user on webpage for some product. There are
 * three kind of events: view, cart, purchased
 */
@Repository
@Qualifier("event")
public class EventDao implements RelationshipDao<Event> {
    private final String CREATE_RELATIONSHIPS = "USING PERIODIC COMMIT 1000 LOAD CSV WITH HEADERS " +
            "FROM 'file:///events.csv' AS line " +
            "MATCH (p:Product),(u:User) " +
            "WHERE p.id=line.product_id AND u.id=line.user_id" +
            " CALL apoc.create.relationship(u, line.event,{weight: line.weight}, p) YIELD rel REMOVE rel.noOp";
    @Override
    public void add(Event event) {}

    /**
     * Load all relationships from event.csv file and save to db. Method is using query saved
     * in CREATE_RELATIONSHIP variable. Additional query is using apoc plugin installed in neo4j db.
     */
    @Override
    public void loadAllRelationships() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            session.run(CREATE_RELATIONSHIPS);

        }
    }

}
