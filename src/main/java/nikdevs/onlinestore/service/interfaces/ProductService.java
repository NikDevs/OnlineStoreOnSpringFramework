package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.repr.ProductRepr;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductRepr> findAll();
    ProductRepr findById(Long id);
    void save(ProductRepr productRepr) throws IOException;
    void remove(Long id);
}
