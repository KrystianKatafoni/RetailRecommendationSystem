package krystiankatafoni.retailrecommendationsystem.dao.generic;

import java.util.Set;

public interface FullTextSearchDao<T> {
    void createIndex();
    void dropIndex();
    Set<T> getNodesWithPhrase(String phrase, double minProbability);
}
