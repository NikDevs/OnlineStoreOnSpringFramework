package nikdevs.onlinestore.controller.admin;

import nikdevs.onlinestore.service.interfaces.CategoryService;
import nikdevs.onlinestore.service.repr.CategoryRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminCategoriesController {

    private final CategoryService categoryService;
    private static final String ADMIN_PAGE = AdminController.ADMIN_PAGE;
    private static final String PAGE = "categories";
    private static final String PAGE_FORM = "category_form";

    @Autowired
    public AdminCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminListPage(Model model) {
        model.addAttribute("activePage", PAGE);
        model.addAttribute("categories", categoryService.findAll());
        return ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/edit")
    public String adminEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("category", categoryService.findById(id));
        return  ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/delete")
    public String adminDelete(Model model, @PathVariable("id") Long id) {
        categoryService.remove(id);
        model.addAttribute("activePage", PAGE);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/create")
    public String adminCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("category", new CategoryRepr());
        return ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @PostMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminUpsert(@Valid CategoryRepr categoryRepr, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", PAGE);

        if (bindingResult.hasErrors()) {
            return ADMIN_PAGE + "/" + PAGE_FORM;
        }

        categoryService.save(categoryRepr);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }
}
