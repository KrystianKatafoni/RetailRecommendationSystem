package krystiankatafoni.retailrecommendationsystem.controllers;

import krystiankatafoni.retailrecommendationsystem.domain.ProductWithSearchScore;
import krystiankatafoni.retailrecommendationsystem.domain.ProductWithWeight;
import krystiankatafoni.retailrecommendationsystem.services.generic.ProductFullTextSearchService;
import krystiankatafoni.retailrecommendationsystem.services.generic.ProductRecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

/**
 * Endpoint for products operations
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    ProductRecommendationService productRecommendationService;
    ProductFullTextSearchService productFullTextSearchService;
    public ProductController(ProductRecommendationService productRecommendationService,
                             ProductFullTextSearchService productFullTextSearchService) {
        this.productRecommendationService = productRecommendationService;
        this.productFullTextSearchService = productFullTextSearchService;
    }

    /**
     * Get recommendation for product and return set of recommended products.
     * @param productId for product with this id will be preparing recommendation, id is a id from customer(fe. relationship db)
     * @param limit limit of recommended products,
     * @return
     */
    @GetMapping("/recommendations")
    public Set<ProductWithWeight> getRecommendationForProduct(@RequestParam("id") Integer productId,
                                                              @RequestParam(name="limit",required = false) Optional<Integer> limit) {
        return productRecommendationService.findRecommendationsWithLimit(productId, limit.orElse(2));
    }

    @GetMapping("/fullTextSearch")
    public Set<ProductWithSearchScore> getNodesWithFullTextSearchPhrase(@RequestParam("phrase") String phrase,
                                                                        @RequestParam(name="minProbability", required = false)
                                                                                Optional<Double> minProbability) {
        return productFullTextSearchService.findNodesWithPhrase(phrase, minProbability.orElse(0.7));
    }
}
