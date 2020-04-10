package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.repr.OrderRepr;
import nikdevs.onlinestore.service.repr.SystemUser;

import java.util.List;

public interface OrderService {

    List<OrderRepr> findAll();
    List<OrderRepr> findAllByUser(SystemUser user);
    OrderRepr findById(Long id);
    void save(OrderRepr orderRepr);
    void remove(Long id);
}
