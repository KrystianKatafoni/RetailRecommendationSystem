package krystiankatafoni.retailrecommendationsystem;

import krystiankatafoni.retailrecommendationsystem.dao.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class which delete all nodes from db on startup and next
 * using dao is loading all data from .csv file to neo4j db
 */
@Component
public class AppStartup implements CommandLineRunner {

    SingleNodeDao categoryDao;
    RelationshipDao eventDao;
    NodeWithRelationshipDao productDao;
    SingleNodeDao brandDao;
    SingleNodeDao userDao;
    NodesDao allNodesDao;

    public AppStartup(@Qualifier("category") SingleNodeDao categoryDao, @Qualifier("event") RelationshipDao eventDao,
                      @Qualifier("product") NodeWithRelationshipDao productDao, @Qualifier("brand") SingleNodeDao brandDao,
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
        long start = System.currentTimeMillis();
        allNodesDao.deleteAll();
        categoryDao.loadAllNodes();
        brandDao.loadAllNodes();
        userDao.loadAllNodes();
        productDao.loadAllNodes();
        productDao.loadAllRelationships();
        eventDao.loadAllRelationships();
        long diff = System.currentTimeMillis()-start;
        System.out.println(Double.toString(diff/1000));
    }
}
