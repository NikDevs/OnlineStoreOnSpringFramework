package nikdevs.onlinestore.controller;

import nikdevs.onlinestore.service.interfaces.CartService;
import nikdevs.onlinestore.service.interfaces.ProductService;
import nikdevs.onlinestore.service.interfaces.SizeService;
import nikdevs.onlinestore.service.model.CartItemRepr;
import nikdevs.onlinestore.service.model.ProductInfo;
import nikdevs.onlinestore.service.model.ProductRepr;
import nikdevs.onlinestore.service.model.SizeRepr;
import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private SizeService sizeService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, SizeService sizeService) {
        this.cartService = cartService;
        this.productService = productService;
        this.sizeService = sizeService;
    }

    @GetMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("cartProducts", cartService.findAllItems());
        return "cart";
    }

    @GetMapping("/cart/{productId}/{sizeId}/delete")
    public String cartRemoveProduct(@PathVariable("productId") long productId, @PathVariable("sizeId") int sizeId) {
        ProductRepr productRepr = productService.findById(productId);
        SizeRepr sizeRepr = sizeService.findById(sizeId);
        ProductInfo productInfo = new ProductInfo(productRepr, sizeRepr);
        cartService.removeItem(productInfo);
        return "cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(CartItemRepr cartItemRepr, HttpServletRequest httpServletRequest) {
        ProductRepr productRepr = productService.findById(cartItemRepr.getProductId());

        if (productRepr != null) {
            cartService.addItemQty(new ProductInfo(productRepr, cartItemRepr.getSize()), cartItemRepr.getQty());
        }
        return "redirect:" + cartItemRepr.getPageUrl();
    }
}
