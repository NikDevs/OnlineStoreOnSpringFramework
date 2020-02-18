package nikdevs.onlinestore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    static final String ADMIN_PAGE = "admin";

    @GetMapping("/" + ADMIN_PAGE)
    public String adminIndexPage(Model model) {
        model.addAttribute("activePage", "None");
        return ADMIN_PAGE + "/index";
    }
}
