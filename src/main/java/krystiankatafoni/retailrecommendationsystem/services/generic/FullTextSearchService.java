package krystiankatafoni.retailrecommendationsystem.services.generic;

import java.util.Set;

public interface FullTextSearchService<T> {
    Set<T> findNodesWithPhrase(String phrase, double minProbability);
}
