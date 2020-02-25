package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.aspect.TrackTime;
import nikdevs.onlinestore.persist.model.Brand;
import nikdevs.onlinestore.persist.repo.BrandRepository;
import nikdevs.onlinestore.service.interfaces.BrandService;
import nikdevs.onlinestore.service.model.BrandRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public void setProductRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @TrackTime
    public List<BrandRepr> findAll() {
        return brandRepository.findAllBrandRepr();
    }

    @TrackTime
    @Override
    public BrandRepr findById(int id) {
        return new BrandRepr(brandRepository.findById(id).get());
    }

    @Override
    @TrackTime
    @Transactional
    public void save(BrandRepr brandRepr) {
        Brand brand = (brandRepr.getId() != null) ? brandRepository.findById(brandRepr.getId()).get() : new Brand();
        brand.setName(brandRepr.getName());
        brandRepository.save(brand);
    }

    @Override
    @TrackTime
    @Transactional
    public void remove(int id) {
        brandRepository.findById(id).ifPresent(brandRepository::delete);
    }
}
