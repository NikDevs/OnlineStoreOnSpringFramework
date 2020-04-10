package nikdevs.onlinestore.controller.admin;

import nikdevs.onlinestore.service.interfaces.*;
import nikdevs.onlinestore.service.repr.ProductRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class AdminProductsController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SizeService sizeService;
    private final BrandService brandService;
    private final PictureService pictureService;
    private static final String ADMIN_PAGE = AdminController.ADMIN_PAGE;
    private static final String PAGE = "products";
    private static final String PAGE_FORM = "product_form";

    @Autowired
    public AdminProductsController(ProductService productService, CategoryService categoryService, SizeService sizeService,
                                   BrandService brandService, PictureService pictureService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.sizeService = sizeService;
        this.brandService = brandService;
        this.pictureService = pictureService;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminListPage(Model model) {
        model.addAttribute("activePage", PAGE);
        model.addAttribute("products", productService.findAll());
        return ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/edit")
    public String adminEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("sizes", sizeService.findAll());
        return  ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/delete")
    public String adminDelete(Model model, @PathVariable("id") Long id) {
        productService.remove(id);
        model.addAttribute("activePage", PAGE);
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/create")
    public String adminCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", PAGE);
        model.addAttribute("product", new ProductRepr());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("sizes", sizeService.findAll());
        return ADMIN_PAGE + "/" + PAGE_FORM;
    }

    @PostMapping("/" + ADMIN_PAGE + "/" + PAGE)
    public String adminUpsert(@Valid ProductRepr productRepr, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", PAGE);

        try {
            productService.save(productRepr);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", true);
            if (productRepr.getId() == null) {
                return "redirect:/" + ADMIN_PAGE + "/" + PAGE + "/create";
            }
            return "redirect:/" + ADMIN_PAGE + "/" + PAGE + "/" + productRepr.getId() + "/edit";
        }
        return "redirect:/" + ADMIN_PAGE + "/" + PAGE;
    }
}
