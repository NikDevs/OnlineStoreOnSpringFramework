package nikdevs.onlinestore.service.model;

import java.util.Objects;

public class ProductInfo {

    private ProductRepr product;
    private SizeRepr size;

    public ProductInfo() {
    }

    public ProductInfo(ProductRepr product, SizeRepr size) {
        this.product = product;
        this.size = size;
    }

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(ProductRepr product) {
        this.product = product;
    }

    public SizeRepr getSize() {
        return size;
    }

    public void setSize(SizeRepr size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo that = (ProductInfo) o;
        return product.equals(that.product) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, size);
    }
}
