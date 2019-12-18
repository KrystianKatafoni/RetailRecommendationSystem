package krystiankatafoni.retailrecommendationsystem.services;

import krystiankatafoni.retailrecommendationsystem.dao.NodeWithRelationshipDao;
import krystiankatafoni.retailrecommendationsystem.domain.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service which help to take another product recommendations based on product on input
 */
@Service
public class ProductRecommendationService implements RecommendationService<Product, Integer> {
    NodeWithRelationshipDao productDao;

    public ProductRecommendationService(@Qualifier("product") NodeWithRelationshipDao productDao) {
        this.productDao = productDao;
    }

    /**
     *
     * @param productId id of the product which is used for recommendations
     * @param limit limit of the recommended products
     * @return collection of recommended products different from productId
     */
    @Override
    public Set<Product> findRecommendationsWithLimit(Integer productId, Integer limit) {
        return productDao.getRecommendationsWithLimit(productId,limit);
    }
}
