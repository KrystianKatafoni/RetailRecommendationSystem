package krystiankatafoni.retailrecommendationsystem.domain;

import krystiankatafoni.retailrecommendationsystem.domain.nodes.Category;

public class CategoryWithAmount {
    private Category category;
    private int amount;

    public CategoryWithAmount(Category category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public CategoryWithAmount() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
