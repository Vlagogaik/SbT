package org.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rest.data.Cart;
import org.rest.data.Client;
import org.rest.data.Product;
import org.rest.repository.CartRepository;
import org.rest.repository.ClientRepository;
import org.rest.repository.ProductRepository;
import org.rest.request.TransferRequest;
import org.rest.service.TransferService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private TransferService transferService;

    private Client testClient;
    private Cart testCart;
    private List<Product> testProducts;

    @BeforeEach
    void setUp() {
        testClient = new Client(1L, "Oleg", "olrg", "password", "oleg@mail", 1L);
        testCart = new Cart(1L, new ArrayList<>(), "promo123");
        testProducts = List.of(
                new Product(1L, "Product A", BigDecimal.valueOf(10.0), 2),
                new Product(2L, "Product B", BigDecimal.valueOf(15.0), 3)
        );
        testCart.setProducts(testProducts);
    }

    @Test
    void testProcessPurchase_Successful() {
        TransferRequest request = new TransferRequest();
        request.setClientId(testClient.getId());
        request.setCartId(testCart.getId());

        when(clientRepository.findById(testClient.getId())).thenReturn(Optional.of(testClient));
        when(cartRepository.findById(testCart.getId())).thenReturn(Optional.of(testCart));
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProducts.get(0)));
        when(productRepository.findById(2L)).thenReturn(Optional.of(testProducts.get(1)));

        assertDoesNotThrow(() -> transferService.processPurchase(request));

        assertEquals(0, testProducts.get(0).getCount());
        assertEquals(0, testProducts.get(1).getCount());
        verify(productRepository, times(2)).update(any(Product.class));
        verify(cartRepository, times(1)).deleteById(testCart.getId());
    }
}
