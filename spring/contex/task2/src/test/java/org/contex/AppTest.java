package org.contex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AppTest {
    @MockBean
    private BankClientsApp bankClientService;
    @MockBean
    private TransferByPhoneApp transferService;
    @MockBean
    private DataBase reposutory;
    @Autowired
    private Application application;

    @Test
    public void testIsClient() {
        Mockito.when(bankClientService.isClient(19)).thenReturn(true);
    }

    @Test
    public void testTransferMoney_UserIsNotClient() throws InvalidTransferException{
        Mockito.when(bankClientService.isClient(1)).thenReturn(false);
        InvalidTransferException e = Assertions.assertThrows(InvalidTransferException.class, () ->{
            application.transferMoney(1, "789423", 10.0);
        });
        Assertions.assertEquals("User is not a client of the bank.", e.getMessage());
    }

    @Test
    public void testTransferMoney_Success() throws InvalidTransferException {
        Mockito.when(bankClientService.isClient(3)).thenReturn(true);
        application.transferMoney(3, "789423", 10.0);


    }
}
