package krystiankatafoni.retailrecommendationsystem.dao;

/**
 * Dao which represents operations on relationships in db
 * @param <T> type of relationship
 */
public interface RelationshipDao<T> {
    void add(T t);
    void loadAllRelationships();
}
