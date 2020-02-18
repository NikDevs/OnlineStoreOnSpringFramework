package nikdevs.onlinestore.controller.admin;

import nikdevs.onlinestore.persist.model.Picture;
import nikdevs.onlinestore.service.interfaces.PictureService;
import nikdevs.onlinestore.service.interfaces.ProductService;
import nikdevs.onlinestore.service.model.ProductRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminPictureController {

    private final PictureService pictureService;

    private static final String ADMIN_PAGE = AdminController.ADMIN_PAGE;
    private static final String PAGE = "pictures";

    @Autowired
    public AdminPictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }


    @GetMapping("/" + PAGE + "/{pictureId}")
    public void adminDownloadProductPicture(@PathVariable("pictureId") Long pictureId,
                                            HttpServletResponse response) throws IOException {
        Picture picture = pictureService.findById(pictureId);
        if (picture != null) {
            response.setContentType(picture.getContentType());
            response.getOutputStream().write(picture.getPictureData().getData());
        }
    }

    @GetMapping("/" + ADMIN_PAGE + "/" + PAGE + "/{id}/{productId}/delete")
    public String adminDelete(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
        pictureService.remove(id);
        return "redirect:/" + ADMIN_PAGE + "/products/" + productId + "/edit";
    }
}
