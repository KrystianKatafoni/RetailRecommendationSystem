package krystiankatafoni.retailrecommendationsystem;

import krystiankatafoni.retailrecommendationsystem.dao.*;
import krystiankatafoni.retailrecommendationsystem.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AppStartup implements CommandLineRunner {


    private final String LOAD_USERS = "LOAD CSV WITH HEADERS FROM 'file:///categories.csv' AS line " +
            "CREATE (:Category { id: line.id, name: line.category})";
    private final String LOAD_BRANDS = "LOAD CSV WITH HEADERS FROM 'file:///categories.csv' AS line " +
            "CREATE (:Category { id: line.id, name: line.category})";
    private final String LOAD_PRODUCTS = "LOAD CSV WITH HEADERS FROM 'file:///categories.csv' AS line " +
            "CREATE (:Category { id: line.id, name: line.category})";
    private final String LOAD_EVENTS = "LOAD CSV WITH HEADERS FROM 'file:///categories.csv' AS line " +
            "CREATE (:Category { id: line.id, name: line.category})";


    SingleNodeDao categoryDao;
    RelationshipDao eventDao;
    SingleNodeDao productDao;
    SingleNodeDao brandDao;
    SingleNodeDao userDao;
    NodesDao allNodesDao;

    public AppStartup(@Qualifier("category") SingleNodeDao categoryDao, @Qualifier("event")RelationshipDao eventDao,
                      @Qualifier("product") SingleNodeDao productDao, @Qualifier("brand") SingleNodeDao brandDao,
                      @Qualifier("user") SingleNodeDao userDao, NodesDao allNodesDao) {
        this.categoryDao = categoryDao;
        this.eventDao = eventDao;
        this.productDao = productDao;
        this.brandDao = brandDao;
        this.userDao = userDao;
        this.allNodesDao = allNodesDao;
    }

    @Override
    public void run(String... args) throws Exception {
        allNodesDao.deleteAll();
        categoryDao.loadAllFromCsv();
        brandDao.loadAllFromCsv();
        userDao.loadAllFromCsv();
        productDao.loadAllFromCsv();
    }
}
