package krystiankatafoni.retailrecommendationsystem.controllers;

import krystiankatafoni.retailrecommendationsystem.domain.CategoryWithAmount;
import krystiankatafoni.retailrecommendationsystem.services.generic.CategoryFavouriteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint for category operations
 */
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    CategoryFavouriteService categoryFavouriteService;

    public CategoryController(CategoryFavouriteService categoryFavouriteService) {
        this.categoryFavouriteService = categoryFavouriteService;
    }

    /**
     * Get favourite category for specified user id
     * @param id
     * @return
     */
    @GetMapping("/favourites")
    public CategoryWithAmount getFavouriteCategoryForUser(@RequestParam("id") String id) {
        return categoryFavouriteService.findFavourite(Integer.parseInt(id));

    }

}
