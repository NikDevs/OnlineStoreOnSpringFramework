package nikdevs.onlinestore.service.model;

import nikdevs.onlinestore.persist.model.Size;

import java.util.Objects;

public class SizeRepr {

    private Integer id;
    private Integer value;
    private Long productCount;

    public SizeRepr() {
    }

    public SizeRepr(Size size) {
        this.id = size.getId();
        this.value = size.getValue();
    }

    public SizeRepr(Integer id, Integer value, Long productCount) {
        this.id = id;
        this.value = value;
        this.productCount = productCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeRepr sizeRepr = (SizeRepr) o;
        return id.equals(sizeRepr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
