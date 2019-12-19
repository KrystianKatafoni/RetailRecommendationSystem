package krystiankatafoni.retailrecommendationsystem.domain.nodes;

import java.util.Objects;

/**
 * Class which represents Product node
 */
public class Product {
    public static final String label = "Product";
    private String id;
    private String description;

    public Product(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
