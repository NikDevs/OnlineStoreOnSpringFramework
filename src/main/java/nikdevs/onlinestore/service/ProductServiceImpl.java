package nikdevs.onlinestore.service;

import nikdevs.onlinestore.persist.model.Category;
import nikdevs.onlinestore.persist.model.Product;
import nikdevs.onlinestore.persist.model.Size;
import nikdevs.onlinestore.persist.repo.ProductRepository;
import nikdevs.onlinestore.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findAllBySize(Size size) {
        return productRepository.findAllBySizes(size);
    }

    @Override
    public Product findById(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void remove(long id) {
        productRepository.delete(findById(id));
    }
}
