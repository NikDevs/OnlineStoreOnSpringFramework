package nikdevs.onlinestore.controller.admin;

import nikdevs.onlinestore.service.interfaces.RoleService;
import nikdevs.onlinestore.service.repr.RoleRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminRolesController {

    private final RoleService roleService;
    private static final String ADMIN_PAGE = AdminController.ADMIN_PAGE;
    private static final String PAGE = "roles";
    private static final String PAGE_FORM = "role_form";

    @Autowired
    public AdminRolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminListPage(Model model) {
        model.addAttribute("activePage", PAGE);
        model.addAttribute("roles", roleService.findAll());
        RoleRepr role_su = roleService.findById(1L);
        model.addAttribute("role_su", role_su);
        return ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/edit")
    public String adminEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("role", roleService.findById(id));
        return  ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/delete")
    public String adminDelete(Model model, @PathVariable("id") Long id) {
        roleService.remove(id);
        model.addAttribute("activePage", PAGE);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/create")
    public String adminCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("role", new RoleRepr());
        return ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @PostMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminUpsert(@Valid RoleRepr roleRepr, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", PAGE);

        if (bindingResult.hasErrors()) {
            return ADMIN_PAGE + "/" + PAGE_FORM;
        }

        roleService.save(roleRepr);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }
}
