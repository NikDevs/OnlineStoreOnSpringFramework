package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.repr.CategoryRepr;

import java.util.List;

public interface CategoryService {

    List<CategoryRepr> findAll();
    CategoryRepr findById(Long id);
    void save(CategoryRepr categoryRepr);
    void remove(Long id);
}
