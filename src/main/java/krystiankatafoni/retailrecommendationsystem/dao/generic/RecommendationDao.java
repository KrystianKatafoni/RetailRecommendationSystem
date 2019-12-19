package krystiankatafoni.retailrecommendationsystem.dao.generic;

import java.util.Set;

/**
 *
 * @param <T>
 * @param <K>
 */
public interface RecommendationDao<T,K> {
    /**
     *
     * @param id id of the product for recommendation
     * @param limit limit for the results
     * @return collections of recommended products
     */
    Set<T> getRecommendationsWithLimit(K id, Integer limit);
}
