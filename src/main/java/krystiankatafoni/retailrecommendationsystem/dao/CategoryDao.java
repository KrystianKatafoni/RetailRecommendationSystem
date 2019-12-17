package krystiankatafoni.retailrecommendationsystem.dao;

import krystiankatafoni.retailrecommendationsystem.domain.Category;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("category")
public class CategoryDao implements SingleNodeDao<Category> {

    private final String LOAD_CATEGORIES = "LOAD CSV WITH HEADERS FROM 'file:///categories.csv' AS line " +
            "CREATE (:Category { id: line.id, name: line.category})";
    public CategoryDao() {
    }

    @Override
    public Optional<Category> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }
    @Override
    public void loadAllFromCsv() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {

            session.run(LOAD_CATEGORIES);
        }
    }
}
