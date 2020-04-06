package nikdevs.onlinestore.service.repr;

import nikdevs.onlinestore.persist.model.Category;

public class CategoryRepr extends StandartRepr {

    private String name;
    private Long productCount;

    public CategoryRepr() {
    }

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public CategoryRepr(Long id, String name, Long productCount) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return name;
    }
}
