package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.model.BrandRepr;

import java.util.List;

public interface BrandService {

    List<BrandRepr> findAll();
    BrandRepr findById(int id);
    void save(BrandRepr brand);
    void remove(int id);
}
