package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.persist.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);
}
