package nikdevs.onlinestore.persist.repo;

import nikdevs.onlinestore.persist.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Integer> {

    Size findByValue(int value);
}
