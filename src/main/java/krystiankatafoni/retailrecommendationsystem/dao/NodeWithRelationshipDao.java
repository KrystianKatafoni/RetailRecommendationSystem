package krystiankatafoni.retailrecommendationsystem.dao;

import java.util.List;
import java.util.Set;

/**
 * Generic DAO interface for operations based on nodes and relationships
 * @param <T> Type of node
 */
public interface NodeWithRelationshipDao<T> extends SingleNodeDao<T>, RelationshipDao<T> {
    /**
     *
     * @param id id of the product for recommendation
     * @param limit limit for the results
     * @return collections of recommended products
     */
    Set<T> getRecommendationsWithLimit(Integer id, Integer limit);
}
