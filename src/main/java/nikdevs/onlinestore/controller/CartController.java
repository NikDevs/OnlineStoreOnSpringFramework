package nikdevs.onlinestore.controller;

import nikdevs.onlinestore.service.interfaces.CartService;
import nikdevs.onlinestore.service.interfaces.ProductService;
import nikdevs.onlinestore.service.interfaces.SizeService;
import nikdevs.onlinestore.service.repr.CartItemRepr;
import nikdevs.onlinestore.service.repr.ProductInfo;
import nikdevs.onlinestore.service.repr.ProductRepr;
import nikdevs.onlinestore.service.repr.SizeRepr;
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

    private ProductInfo getProductInfo(Long productId, Long sizeId) {
        ProductRepr productRepr = productService.findById(productId);
        SizeRepr sizeRepr = sizeService.findById(sizeId);
        return new ProductInfo(productRepr, sizeRepr);
    }

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

    @GetMapping("/cart/{productId}/{sizeId}/addQty")
    public String cartAddQtyProduct(@PathVariable("productId") Long productId, @PathVariable("sizeId") Long sizeId) {
        ProductInfo productInfo = getProductInfo(productId, sizeId);
        cartService.addItemQty(productInfo, 1);
        return "redirect:/cart";
    }

    @GetMapping("/cart/{productId}/{sizeId}/removeQty")
    public String cartRemoveQtyProduct(@PathVariable("productId") Long productId, @PathVariable("sizeId") Long sizeId) {
        ProductInfo productInfo = getProductInfo(productId, sizeId);
        cartService.removeItemQty(productInfo, 1);
        return "redirect:/cart";
    }

    @GetMapping("/cart/{productId}/{sizeId}/delete")
    public String cartRemoveProduct(@PathVariable("productId") Long productId, @PathVariable("sizeId") Long sizeId) {
        ProductInfo productInfo = getProductInfo(productId, sizeId);
        cartService.removeItem(productInfo);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(CartItemRepr cartItemRepr, HttpServletRequest httpServletRequest) {
        if (cartItemRepr.getSize() != null && cartItemRepr.getQty() > 0) {
            ProductRepr productRepr = productService.findById(cartItemRepr.getProductId());

            if (productRepr != null) {
                cartService.addItemQty(new ProductInfo(productRepr, cartItemRepr.getSize()), cartItemRepr.getQty());
            }
        }

        return "redirect:" + cartItemRepr.getPageUrl();
    }
}
