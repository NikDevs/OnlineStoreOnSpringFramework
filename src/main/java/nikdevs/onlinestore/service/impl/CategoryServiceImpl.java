package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.persist.model.Category;
import nikdevs.onlinestore.persist.repo.CategoryRepository;
import nikdevs.onlinestore.service.interfaces.CategoryService;
import nikdevs.onlinestore.service.model.CategoryRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setProductRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryRepr> findAll() {
        return categoryRepository.findAllCategoryRepr();
    }

    @Override
    public CategoryRepr findById(int id) {
        return new CategoryRepr(categoryRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void save(CategoryRepr categoryRepr) {
        Category category = (categoryRepr.getId() != null) ? categoryRepository.findById(categoryRepr.getId()).get() : new Category();
        category.setName(categoryRepr.getName());
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void remove(int id) {
        categoryRepository.findById(id).ifPresent(categoryRepository::delete);
    }
}
