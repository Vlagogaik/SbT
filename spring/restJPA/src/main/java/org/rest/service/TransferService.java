package org.rest.service;

import org.rest.data.Cart;
import org.rest.data.Client;
import org.rest.data.Product;
import org.rest.repository.CartRepository;
import org.rest.repository.ClientRepository;
import org.rest.repository.ProductRepository;
import org.rest.request.TransferRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TransferService {

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public TransferService(CartRepository cartRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void processPurchase(TransferRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<Product> productsInCart = cart.getProducts();

        for (Product product : productsInCart) {
            Product storedProduct = productRepository.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (storedProduct.getQuantity() < product.getQuantity()) {
                throw new RuntimeException("Insufficient quantity available for product: " + storedProduct.getName());
            }

            storedProduct.setQuantity(storedProduct.getQuantity() - product.getQuantity());
            productRepository.update(storedProduct);
        }

        cartRepository.deleteById(request.getCartId());
    }
}