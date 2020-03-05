package nikdevs.onlinestore.flow.checkout;


import nikdevs.onlinestore.controller.MainController;
import nikdevs.onlinestore.service.interfaces.CartService;
import nikdevs.onlinestore.service.interfaces.OrderService;
import nikdevs.onlinestore.service.model.OrderRepr;
import nikdevs.onlinestore.service.model.OrderedProductRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class OrderHandler {

    private MainController mainController;
    private CartService cartService;
    private OrderService orderService;

    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";

    @Autowired
    public OrderHandler(MainController mainController, CartService cartService, OrderService orderService) {
        this.mainController = mainController;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    public OrderModel init() {
        return new OrderModel();
    }

    public void addAdressInfo(OrderModel orderModel, AdressInfo adressInfo) {
        orderModel.setAdressInfo(adressInfo);
    }

    public String validateAdressInfo(AdressInfo adressInfo, MessageContext error) {
        return SUCCESS;
    }

    public String saveAll(OrderModel orderModel, MessageContext error) {
        try {
            OrderRepr orderRepr = new OrderRepr();
            orderRepr.setUser(mainController.getCurrentUser());
            orderRepr.setDate(new Date());
            orderRepr.setCountry(orderModel.getAdressInfo().getCountry());
            orderRepr.setZipcode(orderModel.getAdressInfo().getZipcode());
            orderRepr.setAdress(orderModel.getAdressInfo().getAdress());
            orderRepr.setAdditionalInfo(orderModel.getAdressInfo().getAdditionalInfo());
            orderRepr.setProducts(cartService.findAllItems().entrySet().stream().map(e -> new OrderedProductRepr(e.getKey(), e.getValue())).collect(Collectors.toList()));
            orderService.save(orderRepr);
            cartService.clear();
        } catch (Exception e) {
            error.addMessage(new MessageBuilder()
                    .error()
                    .source("order")
                    .defaultText("Internal error. Can't save order.")
                    .build());
            return FAILURE;
        }
        return SUCCESS;
    }
}
