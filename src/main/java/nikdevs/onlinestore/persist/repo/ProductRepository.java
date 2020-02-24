package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.persist.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
