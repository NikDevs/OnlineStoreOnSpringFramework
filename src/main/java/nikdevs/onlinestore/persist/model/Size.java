package nikdevs.onlinestore.persist.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sizes")
public class Size extends StandartEntity {

    @Column
    private int value;
    @ManyToMany(mappedBy = "sizes")
    List<Product> products;

    public Size() {
    }

    public Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
