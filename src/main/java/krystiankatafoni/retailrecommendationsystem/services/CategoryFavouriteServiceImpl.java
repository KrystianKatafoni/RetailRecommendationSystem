package krystiankatafoni.retailrecommendationsystem.services;

import krystiankatafoni.retailrecommendationsystem.dao.CategoryFavouriteDao;
import krystiankatafoni.retailrecommendationsystem.domain.CategoryWithAmount;
import krystiankatafoni.retailrecommendationsystem.services.generic.CategoryFavouriteService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CategoryFavouriteServiceImpl implements CategoryFavouriteService {

    CategoryFavouriteDao categoryFavouriteDao;

    public CategoryFavouriteServiceImpl(CategoryFavouriteDao categoryFavouriteDao) {
        this.categoryFavouriteDao = categoryFavouriteDao;
    }

    /**
     *
     * @param id id for which favourite should be finded
     * @return
     */
    @Override
    public CategoryWithAmount findFavourite(Integer id) {
        return categoryFavouriteDao.getFavourite(id);
    }
}
