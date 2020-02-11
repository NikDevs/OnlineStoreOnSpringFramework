package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.persist.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> findAll();
    Category findById(int id);
    Category findByName(String name);
    void save(Category category);
    void remove(int id);
}
