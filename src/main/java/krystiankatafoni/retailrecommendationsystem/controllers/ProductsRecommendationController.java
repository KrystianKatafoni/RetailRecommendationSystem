package krystiankatafoni.retailrecommendationsystem.controllers;

import krystiankatafoni.retailrecommendationsystem.domain.Product;
import krystiankatafoni.retailrecommendationsystem.services.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Endpoint for product recommendation based on product id
 */
@RestController
@RequestMapping("/api/v1/recommendation/products")
public class ProductsRecommendationController {

    RecommendationService productRecommendationService;

    public ProductsRecommendationController(RecommendationService productRecommendationService) {
        this.productRecommendationService = productRecommendationService;
    }

    /**
     * Get recommendation for product and return set of recommended products.
     * @param productId for product with this id will be preparing recommendation, id is a id from customer(fe. relationship db)
     * @param limit limit of recommended products,
     * @return
     */
    @GetMapping(value = "/{id}/{limit}")
    public Set<Product> getRecommendationForProduct(@PathVariable("id") Integer productId, @PathVariable("limit") Integer limit) {
        return productRecommendationService.findRecommendationsWithLimit(productId, limit);
    }
}
