package org.rest.service;

import org.rest.data.Cart;
import org.rest.data.Product;
import org.rest.data.ProductCart;
import org.rest.repository.CartRepository;
import org.rest.repository.ProductCartRepository;
import org.rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCartRepository productCartRepository;

    public long addProductToCart(Long cartId, Long productId, int count) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        ProductCart productCart = new ProductCart();
        productCart.setCartId(cartId);
        productCart.setProductId(productId);
        productCart.setCount(count);

        productCartRepository.save(productCart);

        return cart.getId();
    }

    public long removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<ProductCart> productCart = productCartRepository.findAll()
                .stream()
                .filter(pc -> pc.getCartId().equals(cartId) && pc.getProductId().equals(productId))
                .findFirst();

        if (productCart.isPresent()) {
            productCartRepository.deleteById(productCart.get().getId());
        }

        return cart.getId();
    }

    public long updateProductQuantityInCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<ProductCart> productCart = productCartRepository.findAll()
                .stream()
                .filter(pc -> pc.getCartId().equals(cartId) && pc.getProductId().equals(productId))
                .findFirst();

        if (productCart.isPresent()) {
            ProductCart pc = productCart.get();
            pc.setCount(quantity);
            productCartRepository.update(pc);
        } else {
            ProductCart newProductCart = new ProductCart(productCart.get().getId(), cartId, productId, quantity);
            productCartRepository.save(newProductCart);
        }

        return cart.getId();
    }

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public void checkout(Long cartId) {
        logger.info("Executing checkout for cartId: {}", cartId);
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        productCartRepository.findAll()
                .stream()
                .filter(pc -> pc.getCartId().equals(cartId))
                .forEach(pc -> productCartRepository.deleteById(pc.getId()));
        logger.info("Found cart: {}", cart);
    }
}