package org.rest.conroller;

import org.rest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/addProduct/{productId}")
    public long addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int count) {
        return cartService.addProductToCart(cartId, productId, count);
    }

    @DeleteMapping("/{cartId}/removeProduct/{productId}")
    public long removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return cartService.removeProductFromCart(cartId, productId);
    }

    @PutMapping("/{cartId}/updateProductQuantity/{productId}")
    public long updateProductQuantityInCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        return cartService.updateProductQuantityInCart(cartId, productId, quantity);
    }

    @GetMapping("/{cartId}/checkout")
    public void checkout(@PathVariable Long cartId) {
        cartService.checkout(cartId);
    }
}

