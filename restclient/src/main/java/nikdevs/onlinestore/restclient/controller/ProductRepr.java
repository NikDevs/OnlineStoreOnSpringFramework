package nikdevs.onlinestore.restclient.controller;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductRepr implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryRepr category;
    private BrandRepr brand;
    private Set<SizeRepr> sizes;

    public ProductRepr() {
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
        ProductRepr that = (ProductRepr) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductRepr{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", brand=" + brand +
                ", sizes=" + sizes +
                '}';
    }
}
