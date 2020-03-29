package nikdevs.onlinestore.controller.rest;

import com.netflix.discovery.EurekaClient;
import nikdevs.onlinestore.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private final EurekaClient eurekaClient;
    private final ProductService productService;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    public ProductRestController(@Lazy EurekaClient eurekaClient, ProductService productService) {
        this.eurekaClient = eurekaClient;
        this.productService = productService;
    }

    @GetMapping("/")
    public String healthCheck() {
        return eurekaClient.getApplication(appName).getName();
    }

    @GetMapping("/get/{id}")
    public ProductRest getProduct(@PathVariable Long id) {
        return new ProductRest(productService.findById(id));
    }
}
