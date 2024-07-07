package org.contex;

import org.springframework.stereotype.Component;

@Component
public class BankClientsApp {

    public boolean isClient(int userId) {
        return userId > 1;
    }
}

