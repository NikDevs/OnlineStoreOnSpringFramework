package nikdevs.onlinestore.controller.admin;

import nikdevs.onlinestore.service.interfaces.SizeService;
import nikdevs.onlinestore.service.model.SizeRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminSizesController {

    private final SizeService sizeService;
    private static final String ADMIN_PAGE = AdminController.ADMIN_PAGE;
    private static final String PAGE = "sizes";
    private static final String PAGE_FORM = "size_form";

    @Autowired
    public AdminSizesController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminListPage(Model model) {
        model.addAttribute("activePage", PAGE);
        model.addAttribute("sizes", sizeService.findAll());
        return ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/edit")
    public String adminEdit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("size", sizeService.findById(id));
        return  ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/delete")
    public String adminDelete(Model model, @PathVariable("id") Integer id) {
        sizeService.remove(id);
        model.addAttribute("activePage", PAGE);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/create")
    public String adminCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("size", new SizeRepr());
        return ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @PostMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminUpsert(@Valid SizeRepr sizeRepr, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", PAGE);

        if (bindingResult.hasErrors()) {
            return ADMIN_PAGE + "/" + PAGE_FORM;
        }

        sizeService.save(sizeRepr);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }
}
