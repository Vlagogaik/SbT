package org.context.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private BankClientsApp bankClientService;

    @Autowired
    private TransferByPhoneApp transferService;

    @PostMapping
    public ResponseEntity<String> transferMoney(@RequestParam int userId,
                                                @RequestParam String phoneNumber,
                                                @RequestParam double amount) {
        if (!bankClientService.isClient(userId)) {
            return ResponseEntity.badRequest().body("User is not a client of the bank.");
        }

        transferService.transfer(phoneNumber, amount);
        return ResponseEntity.ok("Transfer completed successfully.");
    }
}
