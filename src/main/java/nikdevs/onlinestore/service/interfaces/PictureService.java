package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.persist.model.Picture;
import nikdevs.onlinestore.persist.model.Product;

import java.util.List;

public interface PictureService {

    Picture findById(long id);
    void save(Picture picture);
    void remove(long id);
}
