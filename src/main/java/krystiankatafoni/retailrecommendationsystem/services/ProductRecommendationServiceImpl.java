package krystiankatafoni.retailrecommendationsystem.services;

import krystiankatafoni.retailrecommendationsystem.dao.generic.RecommendationDao;
import krystiankatafoni.retailrecommendationsystem.domain.ProductWithWeight;
import krystiankatafoni.retailrecommendationsystem.services.generic.ProductRecommendationService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service which help to take another product recommendations based on product on input
 */
@Service
public class ProductRecommendationServiceImpl implements ProductRecommendationService {
    RecommendationDao productRecommendationDao;


    public ProductRecommendationServiceImpl(RecommendationDao productRecommendationDao) {
        this.productRecommendationDao = productRecommendationDao;

    }

    /**
     *
     * @param productId id of the product which is used for recommendations
     * @param limit limit of the recommended products
     * @return collection of recommended products different from productId
     */
    @Override
    public Set<ProductWithWeight> findRecommendationsWithLimit(Integer productId, Integer limit) {
        return productRecommendationDao.getRecommendationsWithLimit(productId,limit);
    }
}
