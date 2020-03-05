package nikdevs.onlinestore.controller;

import nikdevs.onlinestore.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    private final MainController mainController;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, MainController mainController) {
        this.orderService = orderService;
        this.mainController = mainController;
    }

    @GetMapping("/order")
    public String checkoutPage(Model model) {
        model.addAttribute("orders", orderService.findAllByUser(mainController.getCurrentUser()));
        return "orders";
    }

    @GetMapping("/order/{id}")
    public String productPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", orderService.findById(id));
        return "order";
    }
}
