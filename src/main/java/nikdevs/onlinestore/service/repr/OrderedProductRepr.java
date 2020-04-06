package nikdevs.onlinestore.service.repr;

import nikdevs.onlinestore.persist.model.OrderedProduct;

import java.math.BigDecimal;

public class OrderedProductRepr extends StandartRepr {

    private ProductRepr product;
    private SizeRepr size;
    private BigDecimal price;
    private Integer qnt;
    private OrderRepr order;

    public OrderedProductRepr(OrderedProduct orderedProduct) {
        this.id = orderedProduct.getId();
        this.product = new ProductRepr(orderedProduct.getProduct());
        this.size = new SizeRepr(orderedProduct.getSize());
        this.price = orderedProduct.getPrice();
        this.qnt = orderedProduct.getCount();
        this.order = new OrderRepr(orderedProduct.getOrder());
    }

    public OrderedProductRepr(OrderedProduct orderedProduct, OrderRepr order) {
        this.id = orderedProduct.getId();
        this.product = new ProductRepr(orderedProduct.getProduct());
        this.size = new SizeRepr(orderedProduct.getSize());
        this.price = orderedProduct.getPrice();
        this.qnt = orderedProduct.getCount();
        this.order = order;
    }

    public OrderedProductRepr(ProductInfo productInfo, Integer qnt) {
        this.product = productInfo.getProduct();
        this.size = productInfo.getSize();
        this.price = productInfo.getProduct().getPrice();
        this.qnt = qnt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQnt() {
        return qnt;
    }

    public void setQnt(Integer qnt) {
        this.qnt = qnt;
    }

    public OrderRepr getOrder() {
        return order;
    }

    public void setOrder(OrderRepr order) {
        this.order = order;
    }
}
