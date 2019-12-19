package krystiankatafoni.retailrecommendationsystem.services;

import krystiankatafoni.retailrecommendationsystem.dao.ProductFullTextSearchDao;
import krystiankatafoni.retailrecommendationsystem.dao.generic.FullTextSearchDao;
import krystiankatafoni.retailrecommendationsystem.domain.ProductWithSearchScore;
import krystiankatafoni.retailrecommendationsystem.services.generic.ProductFullTextSearchService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductFullTextSearchServiceImpl implements ProductFullTextSearchService {

    ProductFullTextSearchDao productFullTextSearchDao;

    public ProductFullTextSearchServiceImpl(ProductFullTextSearchDao productFullTextSearchDao) {
        this.productFullTextSearchDao = productFullTextSearchDao;
    }

    @Override
    public Set<ProductWithSearchScore> findNodesWithPhrase(String phrase, double minProbability) {
        return productFullTextSearchDao.getNodesWithPhrase(phrase,minProbability);
    }
}
