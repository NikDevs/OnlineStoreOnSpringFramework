package nikdevs.onlinestore.controller.admin;

import nikdevs.onlinestore.service.interfaces.BrandService;
import nikdevs.onlinestore.service.model.BrandRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminBrandsController {

    private final BrandService brandService;
    private static final String ADMIN_PAGE = AdminController.ADMIN_PAGE;
    private static final String PAGE = "brands";
    private static final String PAGE_FORM = "brand_form";

    @Autowired
    public AdminBrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminListPage(Model model) {
        model.addAttribute("activePage", PAGE);
        model.addAttribute("brands", brandService.findAll());
        return ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/edit")
    public String adminEdit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("brand", brandService.findById(id));
        return  ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/delete")
    public String adminDelete(Model model, @PathVariable("id") Integer id) {
        brandService.remove(id);
        model.addAttribute("activePage", PAGE);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/create")
    public String adminCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("brand", new BrandRepr());
        return ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @PostMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminUpsert(@Valid BrandRepr brandRepr, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", PAGE);

        if (bindingResult.hasErrors()) {
            return ADMIN_PAGE + "/" + PAGE_FORM;
        }

        brandService.save(brandRepr);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }
}
