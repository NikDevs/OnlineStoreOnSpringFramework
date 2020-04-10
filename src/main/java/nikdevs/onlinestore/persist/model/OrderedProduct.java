package nikdevs.onlinestore.persist.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ordered_products")
public class OrderedProduct extends StandartEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @Column
    private BigDecimal price;
    @Column
    private Integer count;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderedProduct() {
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
