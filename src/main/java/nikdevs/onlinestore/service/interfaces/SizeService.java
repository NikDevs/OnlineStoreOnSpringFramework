package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.repr.SizeRepr;

import java.util.List;

public interface SizeService {

    List<SizeRepr> findAll();
    SizeRepr findById(Long id);
    void save(SizeRepr sizeRepr);
    void remove(Long id);
}
