package nikdevs.onlinestore.service;

import nikdevs.onlinestore.persist.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category findById(long id);
    Category findByName(String name);
    void save(Category category);
    void remove(long id);
}
