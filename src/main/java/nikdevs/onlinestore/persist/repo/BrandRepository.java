package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.service.model.BrandRepr;
import org.springframework.data.jpa.repository.JpaRepository;
import nikdevs.onlinestore.persist.model.Brand;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("select new nikdevs.onlinestore.service.model.BrandRepr(b.id, b.name, count(p.id)) " +
            "from Brand b " +
            "left join b.products p " +
            "group by b.id, b.name")
    List<BrandRepr> findAllBrandRepr();
}
