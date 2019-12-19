package krystiankatafoni.retailrecommendationsystem;

import krystiankatafoni.retailrecommendationsystem.dao.generic.FullTextSearchDao;
import krystiankatafoni.retailrecommendationsystem.dao.generic.NodeDao;
import krystiankatafoni.retailrecommendationsystem.dao.generic.NodesDao;
import krystiankatafoni.retailrecommendationsystem.dao.relationships.RelationshipDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class which delete all nodes from db on startup and next
 * using dao is loading all data from .csv file to neo4j db
 */
@Component
public class AppStartup implements CommandLineRunner {

    NodeDao categoryDao;
    NodeDao productDao;
    NodeDao brandDao;
    NodeDao userDao;
    NodesDao allNodesDao;
    RelationshipDao userProductRelationshipDao;
    RelationshipDao brandProductRelationshipDao;
    RelationshipDao categoryProductRelationshipDao;
    FullTextSearchDao productFullTextSearchDao;
    public AppStartup(@Qualifier("category") NodeDao categoryDao,@Qualifier("product") NodeDao productDao,
                      @Qualifier("brand") NodeDao brandDao, @Qualifier("user") NodeDao userDao,
                      NodesDao allNodesDao, @Qualifier("userProduct") RelationshipDao userProductRelationshipDao,
                      @Qualifier("brandProduct") RelationshipDao brandProductRelationshipDao,
                      @Qualifier("categoryProduct") RelationshipDao categoryProductRelationshipDao,
                      FullTextSearchDao productFullTextSearchDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.brandDao = brandDao;
        this.userDao = userDao;
        this.allNodesDao = allNodesDao;
        this.userProductRelationshipDao = userProductRelationshipDao;
        this.brandProductRelationshipDao = brandProductRelationshipDao;
        this.categoryProductRelationshipDao = categoryProductRelationshipDao;
        this.productFullTextSearchDao = productFullTextSearchDao;
    }

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        allNodesDao.deleteAll();
        productFullTextSearchDao.dropIndex();
        categoryDao.loadCollection();
        brandDao.loadCollection();
        userDao.loadCollection();
        productDao.loadCollection();
        userProductRelationshipDao.loadCollection();
        brandProductRelationshipDao.loadCollection();
        categoryProductRelationshipDao.loadCollection();
        productFullTextSearchDao.createIndex();
        long diff = System.currentTimeMillis()-start;
        System.out.println(Double.toString(diff/1000));
    }
}
