package krystiankatafoni.retailrecommendationsystem.dao.relationships;

/**
 * Dao which represents operations on relationships in db
 * @param <T> type of relationship
 */
public interface RelationshipDao<T> {
    /**
     *
     * @param t
     */
    void addRelationship(T t);

    /**
     * Load all realtionships from source and save to db
     */
    void loadCollection();
}
