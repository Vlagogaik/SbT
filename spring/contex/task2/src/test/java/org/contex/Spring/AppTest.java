package org.context.task2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
@WebMvcTest(TransferController.class)
public class AppTest {
    private BankClientsApp bankClientService = new BankClientsApp();

    @Test
    public void testIsClient() {
        assertTrue(bankClientService.isClient(19));
    }
// нашел в каком-то гайде, что можно так делать тесты, не уверен в их правильности
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankClientsApp bankClientServiceAnother;

    @MockBean
    private TransferByPhoneApp transferService;

    @Test
    public void testTransferMoney_UserIsNotClient() throws Exception {
        when(bankClientServiceAnother.isClient(0)).thenReturn(false);

        mockMvc.perform(post("/transfer")
                        .param("userId", "0")
                        .param("phoneNumber", "1234567890")
                        .param("amount", "100.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User is not a client of the bank."));
    }

    @Test
    public void testTransferMoney_Success() throws Exception {
        when(bankClientServiceAnother.isClient(1918)).thenReturn(true);
        doNothing().when(transferService).transfer("1234567890", 100.00);

        mockMvc.perform(post("/transfer")
                        .param("userId", "1918")
                        .param("phoneNumber", "1234567890")
                        .param("amount", "100.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Transfer completed successfully."));
    }
}
