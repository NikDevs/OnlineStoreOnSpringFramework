package nikdevs.onlinestore.controller;

import nikdevs.onlinestore.service.interfaces.ProductService;
import nikdevs.onlinestore.service.interfaces.UserService;
import nikdevs.onlinestore.service.repr.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private ProductService productService;
    private  UserService userService;

    @Autowired
    public MainController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/shop")
    public String storePage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "shop";
    }

    public SystemUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            SystemUser user = userService.findByUserName(userDetails.getUsername());
            return user;
        }
        return null;
    }
}
