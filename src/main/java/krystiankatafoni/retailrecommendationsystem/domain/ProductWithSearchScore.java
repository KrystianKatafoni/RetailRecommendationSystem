package krystiankatafoni.retailrecommendationsystem.domain;

import krystiankatafoni.retailrecommendationsystem.domain.nodes.Product;

public class ProductWithSearchScore {
    private Product product;
    private double score;

    public ProductWithSearchScore(Product product, double score) {
        this.product = product;
        this.score = score;
    }

    public ProductWithSearchScore() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
