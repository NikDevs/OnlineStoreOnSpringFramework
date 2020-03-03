package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.model.CategoryRepr;

import java.util.List;

public interface CategoryService {

    List<CategoryRepr> findAll();
    CategoryRepr findById(int id);
    void save(CategoryRepr categoryRepr);
    void remove(int id);
}
