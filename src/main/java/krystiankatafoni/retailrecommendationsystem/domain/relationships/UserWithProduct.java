package krystiankatafoni.retailrecommendationsystem.domain.relationships;

import krystiankatafoni.retailrecommendationsystem.domain.nodes.Product;
import krystiankatafoni.retailrecommendationsystem.domain.nodes.User;

/**
 * Class which represents Event
 */
public class UserWithProduct {
    private User user;
    private Product product;
    private String label;
    private int weight;
}
