package krystiankatafoni.retailrecommendationsystem.dao;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AllNodesDao implements NodesDao {
    private final String DELETE_ALL = "MATCH (n) DETACH DELETE n";

    /**
     * Delete all nodes and relationships from DB
     */
    @Override
    public void deleteAll() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            session.run(DELETE_ALL);
        }
    }
}