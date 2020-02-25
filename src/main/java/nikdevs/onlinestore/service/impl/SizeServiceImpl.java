package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.aspect.TrackTime;
import nikdevs.onlinestore.persist.model.Size;
import nikdevs.onlinestore.persist.repo.SizeRepository;
import nikdevs.onlinestore.service.interfaces.SizeService;
import nikdevs.onlinestore.service.model.SizeRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service(value = "sizeService")
public class SizeServiceImpl implements SizeService {

    private SizeRepository sizeRepository;

    @Autowired
    public void setSizeRepository(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    @TrackTime
    public List<SizeRepr> findAll() {
        List<SizeRepr> sizes = sizeRepository.findAllSizeRepr();
        Collections.sort(sizes);
        return sizes;
    }

    @Override
    @TrackTime
    public SizeRepr findById(int id) {
        return new SizeRepr(sizeRepository.findById(id).get());
    }

    @Override
    @TrackTime
    public void save(SizeRepr sizeRepr) {
        Size size = (sizeRepr.getId() != null) ? sizeRepository.findById(sizeRepr.getId()).get() : new Size();
        size.setValue(sizeRepr.getValue());
        sizeRepository.save(size);
    }

    @Override
    @TrackTime
    public void remove(int id) {
        sizeRepository.findById(id).ifPresent(sizeRepository::delete);
    }
}
