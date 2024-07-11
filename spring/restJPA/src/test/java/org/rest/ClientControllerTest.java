package org.rest;

import org.rest.conroller.ClientController;
import org.rest.data.Client;
import org.rest.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setName("Oleg");
        client.setLogin("olegf");
        client.setPassword("password123");
        client.setEmail("mail");

        String requestJson = objectMapper.writeValueAsString(client);
        Mockito.when(clientService.registerClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Oleg"))
                .andExpect(jsonPath("$.login").value("olegf"))
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.email").value("mail"));

    }

    @Test
    public void testGetClientById() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setName("Oleg");

        Mockito.when(clientService.getClientById(anyLong())).thenReturn(Optional.of(client));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(client.getId()))
                .andExpect(jsonPath("$.name").value(client.getName()));
    }

    @Test
    public void testDeleteClient() throws Exception {
        Client client = new Client();
        client.setId(1L);
        client.setName("Oleg");

        Mockito.doNothing().when(clientService).deleteClient(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/clients/{id}",1))
                .andExpect(status().isOk());
    }


}
