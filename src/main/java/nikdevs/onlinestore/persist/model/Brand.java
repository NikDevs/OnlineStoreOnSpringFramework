package nikdevs.onlinestore.persist.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends StandartEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "brand")
    private List<Product> products;

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
