package org.context.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferByPhoneApp {
    @Autowired
    private DataBase repository;

    public void transfer(String phoneNumber, double amount) {
        DataBase data = new DataBase();
        data.setPhoneNumber(phoneNumber);
        data.setAmount(amount);
        repository.save(data);
    }
}
