package krystiankatafoni.retailrecommendationsystem.domain;

import java.util.Objects;

/**
 * Class which represents Product node
 */
public class Product {
    private String id;
    private String description;
    private int weightSum;

    public Product(String id, String description, int weightSum) {
        this.id = id;
        this.description = description;
        this.weightSum = weightSum;
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

    public int getWeightSum() {
        return weightSum;
    }

    public void setWeightSum(int weightSum) {
        this.weightSum = weightSum;
    }
}
