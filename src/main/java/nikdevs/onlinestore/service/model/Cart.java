package nikdevs.onlinestore.service.model;

import nikdevs.onlinestore.persist.model.Product;
import nikdevs.onlinestore.persist.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Component
@SessionScope
public class Cart {

    private User user;
    private Map<Product, Integer> products;

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
        this.products = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void plusProduct(Product product) {
        int count = products.getOrDefault(product, 0);
        products.put(product, count + 1);
    }

    public void minusProduct(Product product) {
        int count = products.getOrDefault(product, 0);
        if (count > 0)
            products.put(product, count - 1);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
