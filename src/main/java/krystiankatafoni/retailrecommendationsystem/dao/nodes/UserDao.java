package krystiankatafoni.retailrecommendationsystem.dao.nodes;

import krystiankatafoni.retailrecommendationsystem.dao.DatabaseConnection;
import krystiankatafoni.retailrecommendationsystem.dao.generic.NodeDao;
import krystiankatafoni.retailrecommendationsystem.domain.nodes.User;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Dao which represents operations on user nodes in db
 */
@Repository
@Qualifier("user")
public class UserDao implements NodeDao<User> {
    private final String LOAD_USERS = "LOAD CSV WITH HEADERS FROM 'file:///users.csv' AS line " +
            "CREATE (:User { id: line.id, age: line.age, gender:line.gender})";

    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    /**
     * Load all nodes from user.csv file and save to db. Method is using query saved
     * in LOAD_USERS variable
     */
    @Override
    public void loadCollection() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
                Session session = driver.session()) {
            session.run(LOAD_USERS);
        }
    }
}
