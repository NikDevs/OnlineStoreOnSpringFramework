package nikdevs.onlinestore.service.repr;

import nikdevs.onlinestore.persist.model.Size;

public class SizeRepr extends StandartRepr implements Comparable<SizeRepr> {

    private Integer value;
    private Long productCount;

    public SizeRepr() {
    }

    public SizeRepr(Size size) {
        this.id = size.getId();
        this.value = size.getValue();
    }

    public SizeRepr(Long id, Integer value, Long productCount) {
        this.id = id;
        this.value = value;
        this.productCount = productCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
