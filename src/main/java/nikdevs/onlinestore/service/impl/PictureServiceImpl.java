package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.persist.model.Picture;
import nikdevs.onlinestore.persist.repo.PictureRepository;
import nikdevs.onlinestore.service.interfaces.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;

    @Autowired
    public void setProductRepository(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Picture findById(long id) {
        return pictureRepository.getOne(id);
    }

    @Override
    @Transactional
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    @Transactional
    public void remove(long id) {
        pictureRepository.delete(findById(id));
    }
}
