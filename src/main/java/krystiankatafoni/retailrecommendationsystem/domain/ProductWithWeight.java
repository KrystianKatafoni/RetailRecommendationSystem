package krystiankatafoni.retailrecommendationsystem.domain;

import krystiankatafoni.retailrecommendationsystem.domain.nodes.Product;

public class ProductWithWeight {
    private Product product;
    private int weightSum;

    public ProductWithWeight(Product product, int weightSum) {
        this.product = product;
        this.weightSum = weightSum;
    }

    public ProductWithWeight() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getWeightSum() {
        return weightSum;
    }

    public void setWeightSum(int weightSum) {
        this.weightSum = weightSum;
    }
}
