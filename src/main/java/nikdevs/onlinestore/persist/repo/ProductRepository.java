package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.persist.model.Category;
import nikdevs.onlinestore.persist.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);
    Product findByCode(String code);
}
