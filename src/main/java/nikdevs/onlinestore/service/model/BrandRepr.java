package nikdevs.onlinestore.service.model;

import nikdevs.onlinestore.persist.model.Brand;

import java.io.Serializable;

public class BrandRepr implements Serializable {

    private Integer id;
    private String name;
    private Long productCount;

    public BrandRepr() {
    }

    public BrandRepr(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
    }

    public BrandRepr(Integer id, String name, Long productCount) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
