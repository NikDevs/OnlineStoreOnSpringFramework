package nikdevs.onlinestore.service;

import nikdevs.onlinestore.persist.model.Size;
import nikdevs.onlinestore.persist.repo.SizeRepository;
import nikdevs.onlinestore.service.interfaces.SizeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SizeServiceImpl implements SizeService {

    private SizeRepository sizeRepository;

    @Autowired
    public void setSizeRepository(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findById(int id) {
        return sizeRepository.getOne(id);
    }

    @Override
    public Size findByValue(int value) {
        return sizeRepository.findByValue(value);
    }

    @Override
    public void save(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void remove(int id) {
        sizeRepository.delete(findById(id));
    }
}
