package org.context.task2;

import org.springframework.stereotype.Service;

@Service
public class BankClientsApp {

    public boolean isClient(int userId) {
        if (userId > 1) {
            return true;
        }else{
        return false;
        }
    }

}
