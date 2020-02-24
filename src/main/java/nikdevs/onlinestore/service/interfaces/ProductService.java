package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.model.ProductRepr;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductRepr> findAll();
    ProductRepr findById(long id);
    void save(ProductRepr productRepr) throws IOException;
    void remove(long id);
}
