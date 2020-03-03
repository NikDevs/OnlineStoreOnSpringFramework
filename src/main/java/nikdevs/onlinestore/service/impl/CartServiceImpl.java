package nikdevs.onlinestore.service.impl;

import nikdevs.onlinestore.service.interfaces.CartService;
import nikdevs.onlinestore.service.model.ProductInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service(value = "cartService")
@SessionScope
public class CartServiceImpl implements CartService, Serializable {

    private Map<ProductInfo, Integer> cartItems;

    public CartServiceImpl() {
        cartItems = new ConcurrentHashMap<>();
    }

    @Override
    public void addItemQty(ProductInfo prodInfo, int qty) {
        cartItems.put(prodInfo, cartItems.getOrDefault(prodInfo, 0) + qty);
    }

    @Override
    public void removeItemQty(ProductInfo prodInfo, int qty) {
        int currentQty = cartItems.getOrDefault(prodInfo, 0);
        if (currentQty - qty > 0) {
            cartItems.put(prodInfo, currentQty - qty);
        } else {
            cartItems.remove(prodInfo);
        }
    }

    @Override
    public void removeItem(ProductInfo productInfo) {
        cartItems.remove(productInfo);
    }

    @Override
    public Map<ProductInfo, Integer> findAllItems() {
        return Collections.unmodifiableMap(cartItems);
    }

    @Override
    public Integer getItemsQty() {
        return cartItems.size();
    }

    @Override
    public BigDecimal getSubTotal() {
        return cartItems.entrySet().stream()
                .map(e -> e.getKey().getProduct().getPrice().multiply(new BigDecimal(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void clear() {
        cartItems.clear();
    }
}
