package org.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.rest.conroller.CartController;
import org.rest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testAddProductToCart() throws Exception {
        long cartId = 1L;
        long productId = 1L;
        int count = 5;

        Mockito.when(cartService.addProductToCart(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt())).thenReturn(cartId);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/carts/{cartId}/addProduct/{productId}", cartId, productId)
                        .param("count", String.valueOf(count))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(cartId)));
    }

    @Test
    public void testRemoveProductFromCart() throws Exception {
        long cartId = 1L;
        long productId = 1L;

        Mockito.when(cartService.removeProductFromCart(Mockito.anyLong(), Mockito.anyLong())).thenReturn(cartId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/carts/{cartId}/removeProduct/{productId}", cartId, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(cartId)));
    }

    @Test
    public void testUpdateProductQuantityInCart() throws Exception {
        long cartId = 1L;
        long productId = 1L;
        int quantity = 5;

        Mockito.when(cartService.updateProductQuantityInCart(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt())).thenReturn(cartId);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/carts/{cartId}/updateProductQuantity/{productId}", cartId, productId)
                        .param("quantity", String.valueOf(quantity))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(cartId)));
    }

    @Test
    public void testCheckout() throws Exception {
        long cartId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/carts/{cartId}/checkout", cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
