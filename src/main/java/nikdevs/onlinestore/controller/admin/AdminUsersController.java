package nikdevs.onlinestore.controller.admin;

import nikdevs.onlinestore.controller.MainController;
import nikdevs.onlinestore.persist.model.User;
import nikdevs.onlinestore.service.interfaces.RoleService;
import nikdevs.onlinestore.service.interfaces.UserService;
import nikdevs.onlinestore.service.repr.RoleRepr;
import nikdevs.onlinestore.service.repr.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminUsersController {

    private final RoleService roleService;
    private final UserService userService;
    private final MainController mainController;

    private static final String ADMIN_PAGE = AdminController.ADMIN_PAGE;
    private static final String PAGE = "users";
    private static final String PAGE_FORM = "user_form";

    @Autowired
    public AdminUsersController(RoleService roleService, @Lazy UserService userService, MainController mainController) {
        this.roleService = roleService;
        this.userService = userService;
        this.mainController = mainController;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminListPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("role_su", roleService.findById(1L));
        return ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/edit")
    public String adminEdit(Model model, @PathVariable("id") Long id) {
        SystemUser user = mainController.getCurrentUser();
        List<RoleRepr> roles;
        if (user != null && user.getRoles().contains(roleService.findById(1L))) {
            roles = roleService.findAll();
        } else {
            roles = roleService.findAllWithOutSU();
        }
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roles);
        return  ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/editCurrent")
    public String adminEditCurrentUser() {
        SystemUser user = mainController.getCurrentUser();
        if (user != null) {
            return "redirect:/" + ADMIN_PAGE + "/" + PAGE + "/" + user.getId() + "/edit";
        }
        return "redirect:/" + ADMIN_PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/delete")
    public String adminDelete(Model model, @PathVariable("id") Long id) {
        userService.remove(id);
        model.addAttribute("activePage", "Users");
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/create")
    public String adminCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAllWithOutSU());
        return  ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @PostMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminUpsert(@Valid SystemUser user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");

        if (bindingResult.hasErrors()) {
            return  ADMIN_PAGE + "/" + PAGE_FORM;
        }

        userService.save(user);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }
}
