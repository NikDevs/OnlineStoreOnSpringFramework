package nikdevs.onlinestore.service;

import nikdevs.onlinestore.persist.model.Category;
import nikdevs.onlinestore.persist.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(long id);
    void save(Product product);
    void remove(long id);
    List<Product> findAllByCategory(Category category);
    Product findByCode(String code);
}
