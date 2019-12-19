package krystiankatafoni.retailrecommendationsystem.services.generic;

/**
 * Generic interface for finding favourites
 * @param <T> object returning as favourite
 * @param <K> type of object inserting as id for finding
 */
public interface FavouriteService<T, K> {
    /**
     * find favourite object for specified id
     * @param id id for which favourite should be finded
     * @return favourite object
     */
    T findFavourite(K id);
}
