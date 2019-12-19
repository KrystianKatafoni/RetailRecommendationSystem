package krystiankatafoni.retailrecommendationsystem.dao.nodes;

import krystiankatafoni.retailrecommendationsystem.dao.DatabaseConnection;
import krystiankatafoni.retailrecommendationsystem.dao.generic.NodeDao;
import krystiankatafoni.retailrecommendationsystem.domain.nodes.Brand;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Dao which represents operations in db on brands fe. samsung, apple etc.
 */
@Repository
@Qualifier("brand")
public class BrandDao implements NodeDao<Brand> {
    private final String LOAD_BRANDS = "LOAD CSV WITH HEADERS FROM 'file:///brands.csv' AS line " +
            "CREATE (:Brand { id: line.id, name: line.brand})";

    @Override
    public Optional<Brand> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Brand> getAll() {
        return null;
    }

    @Override
    public void save(Brand brand) {

    }

    @Override
    public void update(Brand brand) {

    }

    @Override
    public void delete(Brand brand) {

    }

    /**
     * Load all nodes from brand.csv file and save to db. Method is using query saved
     * in LOAD_BRANDS variable
     */
    @Override
    public void loadCollection() {
        try (Driver driver = GraphDatabase.driver(DatabaseConnection.URI,
                AuthTokens.basic(DatabaseConnection.USERNAME, DatabaseConnection.PASSWORD));
             Session session = driver.session()) {
            session.run(LOAD_BRANDS);
        }
    }
}
