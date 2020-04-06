package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.aspect.TrackTime;
import nikdevs.onlinestore.persist.model.*;
import nikdevs.onlinestore.persist.repo.BrandRepository;
import nikdevs.onlinestore.persist.repo.CategoryRepository;
import nikdevs.onlinestore.persist.repo.ProductRepository;
import nikdevs.onlinestore.persist.repo.SizeRepository;
import nikdevs.onlinestore.service.interfaces.ProductService;
import nikdevs.onlinestore.service.repr.ProductRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService, Serializable {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    private SizeRepository sizeRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              BrandRepository brandRepository, SizeRepository sizeRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    @TrackTime
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Override
    @TrackTime
    public ProductRepr findById(Long id) {
        return new ProductRepr(productRepository.findById(id).get());
    }

    @Override
    @TrackTime
    @Transactional
    public void save(ProductRepr productRepr) throws IOException {
        Product product = (productRepr.getId() != null) ? productRepository.findById(productRepr.getId()).get() : new Product();
        product.setCode(productRepr.getCode());
        product.setName(productRepr.getName());
        product.setDescription(productRepr.getDescription());
        product.setPrice(productRepr.getPrice());
        product.setCategory(categoryRepository.findById(productRepr.getCategory().getId()).get());
        product.setBrand(brandRepository.findById(productRepr.getBrand().getId()).get());
        product.setSizes(productRepr.getSizes()
                .stream().map(sizeRepr -> sizeRepository.findById(sizeRepr.getId()).get()).collect(Collectors.toSet()));

        MultipartFile[] newPictures = productRepr.getNewPictures();
        if (newPictures != null) {
            for (MultipartFile newPicture : newPictures) {
                if (newPicture.isEmpty()) continue;
                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(newPicture.getOriginalFilename(),
                        newPicture.getContentType(), new PictureData(newPicture.getBytes())));
            }
        }
        productRepository.save(product);
    }

    @Override
    @TrackTime
    @Transactional
    public void remove(Long id) {
        productRepository.findById(id).ifPresent(productRepository::delete);
    }
}
