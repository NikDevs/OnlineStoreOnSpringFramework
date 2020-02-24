package nikdevs.onlinestore.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import nikdevs.onlinestore.persist.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {

}
