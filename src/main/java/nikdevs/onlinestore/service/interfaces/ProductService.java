package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.persist.model.Category;
import nikdevs.onlinestore.persist.model.Product;
import nikdevs.onlinestore.persist.model.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> findAll();
    Product findById(long id);
    void save(Product product);
    void remove(long id);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllBySize(Size size);
    Product findByCode(String code);
}
