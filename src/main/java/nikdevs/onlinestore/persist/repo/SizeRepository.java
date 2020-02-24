package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.persist.model.Size;
import nikdevs.onlinestore.service.model.SizeRepr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {

    @Query("select new nikdevs.onlinestore.service.model.SizeRepr(s.id, s.value, count(p.id)) " +
            "from Size s " +
            "left join s.products p " +
            "group by s.id, s.value")
    List<SizeRepr> findAllSizeRepr();
}
