package nikdevs.onlinestore.restclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("get/{id}")
    public String getProduct(@PathVariable Long id) {
        ResponseEntity<ProductRepr> result = null;
        try {
            result = restTemplate.getForEntity("http://ONLINESTORE/api/get/" + id, ProductRepr.class);
        } catch (RestClientException ignored) {
        }
        if (result.getStatusCode() == HttpStatus.OK) {
            System.out.println(result.getBody().getId());
        }
        return result.getBody().toString();
    }
}
