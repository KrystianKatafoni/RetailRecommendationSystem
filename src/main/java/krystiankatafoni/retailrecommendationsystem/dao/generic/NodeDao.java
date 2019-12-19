package krystiankatafoni.retailrecommendationsystem.dao.generic;

import java.util.List;
import java.util.Optional;

/**
 * Generic Dao interface which represents operations on nodes
 * @param <T>
 */
public interface NodeDao<T> {
    Optional<T> get(int id);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(T t);

    /**
     *
     */
    void loadCollection();
}
