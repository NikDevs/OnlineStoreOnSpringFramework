package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.persist.model.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SizeService {

    List<Size> findAll();
    Size findById(int id);
    void save(Size size);
    void remove(int id);
    Size findByValue(int value);
}
