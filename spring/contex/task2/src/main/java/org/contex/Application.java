package org.contex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private TransferByPhoneApp transferService;
    private BankClientsApp bankClientService;
    private DataBase repository;

    @Autowired
    public Application(TransferByPhoneApp transferService, BankClientsApp bankClientService, DataBase repository){
        this.transferService = transferService;
        this.bankClientService = bankClientService;
        this.repository = repository;
    }

    public void transferMoney(int userId, String phoneNumber, double amount) throws InvalidTransferException{
        if (!bankClientService.isClient(userId)) {
            throw new InvalidTransferException("User is not a client of the bank.");
        }else{
            transferService.transfer(phoneNumber, amount);
            repository.save(repository, userId, phoneNumber, amount);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
