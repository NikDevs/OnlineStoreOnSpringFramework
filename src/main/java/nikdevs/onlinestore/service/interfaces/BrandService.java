package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.repr.BrandRepr;

import java.util.List;

public interface BrandService {

    List<BrandRepr> findAll();
    BrandRepr findById(Long id);
    void save(BrandRepr brand);
    void remove(Long id);
}
