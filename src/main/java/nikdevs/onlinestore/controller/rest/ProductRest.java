package nikdevs.onlinestore.controller.rest;

import nikdevs.onlinestore.service.repr.BrandRepr;
import nikdevs.onlinestore.service.repr.CategoryRepr;
import nikdevs.onlinestore.service.repr.ProductRepr;
import nikdevs.onlinestore.service.repr.SizeRepr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductRest implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryRepr category;
    private BrandRepr brand;
    private Set<SizeRepr> sizes;

    public ProductRest() {
    }

    public ProductRest(ProductRepr productRepr) {
        this.id = productRepr.getId();
        this.code = productRepr.getCode();
        this.name = productRepr.getName();
        this.description = productRepr.getDescription();
        this.price = productRepr.getPrice();
        this.category = productRepr.getCategory();
        this.brand = productRepr.getBrand();
        this.sizes = productRepr.getSizes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryRepr getCategory() {
        return category;
    }

    public void setCategory(CategoryRepr category) {
        this.category = category;
    }

    public BrandRepr getBrand() {
        return brand;
    }

    public void setBrand(BrandRepr brand) {
        this.brand = brand;
    }

    public Set<SizeRepr> getSizes() {
        return sizes;
    }

    public void setSizes(Set<SizeRepr> sizes) {
        this.sizes = sizes;
    }

    public String getSizesString() {
        return getSizes().stream().map(SizeRepr::toString).collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRest that = (ProductRest) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
