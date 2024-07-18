package org.rest.service;

import org.rest.data.Client;
import org.rest.data.Product;
import org.rest.data.ProductCart;
import org.rest.repository.CartRepository;
import org.rest.repository.ClientRepository;
import org.rest.repository.ProductCartRepository;
import org.rest.repository.ProductRepository;
import org.rest.request.TransferRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ProductCartRepository productCartRepository;

    public TransferService(CartRepository cartRepository, ClientRepository clientRepository, ProductRepository productRepository, ProductCartRepository productCartRepository) {
        this.cartRepository = cartRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.productCartRepository = productCartRepository;
    }

    @Transactional
    public void processPurchase(TransferRequest request) {

        List<ProductCart> productCarts = productCartRepository.findByCartId(request.getCartId());
        for (ProductCart productCart : productCarts) {
            Long productId = productCart.getProductId();
            int productCount = productCart.getCount();

            if (productId == null || productCount <= 0) {
                throw new RuntimeException("Invalid product in cart");
            }

            productCount--;

            productCart.setCount(productCount);
            productCartRepository.update(productCart);

            if (productCount == 0) {
                productCartRepository.deleteById(productCart.getId());
            }

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int currentProductCount = product.getCount();

            if (currentProductCount <= 0) {
                throw new RuntimeException("Insufficient quantity available for product: " + product.getName());
            }

            currentProductCount--;
            product.setCount(currentProductCount);

            productRepository.update(product);

        }
    }
}