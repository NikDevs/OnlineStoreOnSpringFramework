package nikdevs.onlinestore.service.interfaces;

import nikdevs.onlinestore.service.model.OrderRepr;
import nikdevs.onlinestore.service.model.SystemUser;

import java.util.List;

public interface OrderService {

    List<OrderRepr> findAll();
    List<OrderRepr> findAllByUser(SystemUser user);
    OrderRepr findById(long id);
    void save(OrderRepr orderRepr);
    void remove(long id);
}
