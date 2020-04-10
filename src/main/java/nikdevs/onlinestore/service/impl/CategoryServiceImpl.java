package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.aspect.TrackTime;
import nikdevs.onlinestore.persist.model.Category;
import nikdevs.onlinestore.persist.repo.CategoryRepository;
import nikdevs.onlinestore.service.interfaces.CategoryService;
import nikdevs.onlinestore.service.repr.CategoryRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService, Serializable {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setProductRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @TrackTime
    public List<CategoryRepr> findAll() {
        return categoryRepository.findAllCategoryRepr();
    }

    @Override
    @TrackTime
    public CategoryRepr findById(Long id) {
        return new CategoryRepr(categoryRepository.findById(id).get());
    }

    @Override
    @TrackTime
    @Transactional
    public void save(CategoryRepr categoryRepr) {
        Category category = (categoryRepr.getId() != null) ? categoryRepository.findById(categoryRepr.getId()).get() : new Category();
        category.setName(categoryRepr.getName());
        categoryRepository.save(category);
    }

    @Override
    @TrackTime
    @Transactional
    public void remove(Long id) {
        categoryRepository.findById(id).ifPresent(categoryRepository::delete);
    }
}
