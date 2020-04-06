package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.persist.model.Order;
import nikdevs.onlinestore.persist.model.OrderedProduct;
import nikdevs.onlinestore.persist.repo.*;
import nikdevs.onlinestore.service.interfaces.OrderService;
import nikdevs.onlinestore.service.repr.OrderRepr;
import nikdevs.onlinestore.service.repr.OrderedProductRepr;
import nikdevs.onlinestore.service.repr.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private OrderedProductsRepository orderedProductsRepository;
    private ProductRepository productRepository;
    private SizeRepository sizeRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderedProductsRepository orderedProductsRepository, ProductRepository productRepository, SizeRepository sizeRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderedProductsRepository = orderedProductsRepository;
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public List<OrderRepr> findAll() {
        return orderRepository.findAll().stream().map(OrderRepr::new).collect(Collectors.toList());
    }

    @Override
    public OrderRepr findById(Long id) {
        return new OrderRepr(orderRepository.findById(id).get());
    }

    @Override
    public void save(OrderRepr orderRepr) {
        Order order = (orderRepr.getId() != null) ? orderRepository.findById(orderRepr.getId()).get() : new Order();
        order.setUser(userRepository.findById(orderRepr.getUser().getId()).get());
        order.setDate(orderRepr.getDate());
        order.setCountry(orderRepr.getCountry());
        order.setZipcode(orderRepr.getZipcode());
        order.setAdditionalInfo(orderRepr.getAdditionalInfo());
        order.setAdress(orderRepr.getAdress());
        orderRepository.save(order);
        for (OrderedProductRepr opr : orderRepr.getProducts()) {
            OrderedProduct op = (opr.getId() != null) ? orderedProductsRepository.findById(opr.getId()).get() : new OrderedProduct();
            op.setProduct(productRepository.findById(opr.getProduct().getId()).get());
            op.setSize(sizeRepository.findById(opr.getSize().getId()).get());
            op.setCount(opr.getQnt());
            op.setPrice(opr.getPrice());
            op.setOrder(order);
            orderedProductsRepository.save(op);
        }
    }

    @Override
    public void remove(Long id) {
        orderRepository.findById(id).ifPresent(orderRepository::delete);
    }

    @Override
    public List<OrderRepr> findAllByUser(SystemUser user) {
        return orderRepository.findAllByUser(userRepository.findById(user.getId()).get()).stream().map(OrderRepr::new).collect(Collectors.toList());
    }
}
