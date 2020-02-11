package nikdevs.onlinestore.controller;

import nikdevs.onlinestore.persist.model.User;
import nikdevs.onlinestore.service.interfaces.RoleService;
import nikdevs.onlinestore.service.interfaces.UserService;
import nikdevs.onlinestore.service.model.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminController(RoleService roleService, @Lazy UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminIndexPage(Model model) {
        model.addAttribute("activePage", "None");
        return "admin/index";
    }

    @GetMapping("/admin/users")
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String adminEditUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        return "admin/user_form";
    }

    @GetMapping("/admin/user/create")
    public String adminCreateUser(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "admin/user_form";
    }

    @PostMapping("/admin/user")
    public String adminUpsertUser(@Valid SystemUser user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");

        if (bindingResult.hasErrors()) {
            return "admin/user_form";
        }

        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/roles")
    public String adminRolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        return "admin/roles";
    }

    @GetMapping("/admin/categories")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        return "admin/categories";
    }

    @GetMapping("/admin/products")
    public String adminProductsPage(Model model) {
        model.addAttribute("activePage", "Products");
        return "admin/products";
    }

    @GetMapping("/admin/sizes")
    public String adminSizesPage(Model model) {
        model.addAttribute("activePage", "Sizes");
        return "admin/sizes";
    }
}
