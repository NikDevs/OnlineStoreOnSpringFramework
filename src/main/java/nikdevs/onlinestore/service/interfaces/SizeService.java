package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.model.SizeRepr;

import java.util.List;

public interface SizeService {

    List<SizeRepr> findAll();
    SizeRepr findById(int id);
    void save(SizeRepr sizeRepr);
    void remove(int id);
}
