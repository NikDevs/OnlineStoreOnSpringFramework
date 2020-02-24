package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.persist.model.Category;
import nikdevs.onlinestore.service.model.CategoryRepr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select new nikdevs.onlinestore.service.model.CategoryRepr(c.id, c.name, count(p.id)) " +
            "from Category c " +
            "left join c.products p " +
            "group by c.id, c.name")
    List<CategoryRepr> findAllCategoryRepr();
}
