package krystiankatafoni.retailrecommendationsystem.services;

import java.util.List;
import java.util.Set;

/**
 * Generic interface for recommendation services
 * @param <T> Type of node or relationship for return
 * @param <K> Type of the object id inserted on input
 */
public interface RecommendationService<T, K> {
        Set<T> findRecommendationsWithLimit(K id, Integer limit);
}
