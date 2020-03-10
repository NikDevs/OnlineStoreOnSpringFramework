package nikdevs.onlinestore.restclient.controller;

import java.io.Serializable;
import java.util.Objects;

public class SizeRepr implements Comparable<SizeRepr>, Serializable {

    private Integer id;
    private Integer value;
    private Long productCount;

    public SizeRepr() {
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

    @Override
    public int compareTo(SizeRepr o) {
        if (this.value >= o.value)
            return 1;
        return -1;
    }
}
