package krystiankatafoni.retailrecommendationsystem.dao.generic;

public interface FavouriteDao<T,K>{
    /**
     *
     * @param id id of object for which will be using favourite
     * @return favourite object
     */
    T getFavourite(K id);
}
