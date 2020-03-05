package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.persist.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductsRepository extends JpaRepository<OrderedProduct, Long> {
}
