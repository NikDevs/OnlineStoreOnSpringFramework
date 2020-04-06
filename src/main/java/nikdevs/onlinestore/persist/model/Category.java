package nikdevs.onlinestore.persist.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends StandartEntity {

    @Column
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
