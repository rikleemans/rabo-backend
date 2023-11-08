package rabobankAPI.API.Controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rabobankAPI.API.DTO.CharityTransactionDTO;
import rabobankAPI.API.DTO.TransactionDTO;
import rabobankAPI.API.DTO.UserTransactionDTO;
import rabobankAPI.API.Enum.Account;
import rabobankAPI.API.Model.Transaction;
import rabobankAPI.API.ServiceInterface.ITransactionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final ITransactionService iTransactionService;

    public TransactionController(@Qualifier("TransactionService") ITransactionService iTransactionService) {
        this.iTransactionService = iTransactionService;
    }

    @PostMapping("charity")
    public ResponseEntity<Transaction> createTransactionCharity(@RequestBody TransactionDTO transactionDTO) {
        Transaction result = iTransactionService.createTransaction(transactionDTO, Account.CHARITY);
        if (result != null) {
            String url = "transaction" + "/" + result.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
        String entity = "Transaction was not created. Please try again later.";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @PostMapping("user")
    public ResponseEntity<Transaction> createTransactionUser(@RequestBody TransactionDTO transactionDTO) {
        Transaction result = iTransactionService.createTransaction(transactionDTO, Account.APPUSER);
        if (result != null) {
            String url = "transaction" + "/" + result.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
        String entity = "Transaction was not created. Please try again later.";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Transaction> getTransactionResponseEntity(Transaction result) {
        if (result != null) {
            String url = "transaction" + "/" + result.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
        String entity = "Transaction was not created. Please try again.";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @GetMapping("{id}")
    //GET at http://localhost:8082/transaction/id
    public ResponseEntity<Transaction> getTransactionById(@PathVariable(value = "id") Long id) {
        Transaction result = iTransactionService.getTransactionById(id);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        }
        String e = "Transaction was not found.";
        return new ResponseEntity(e, HttpStatus.NOT_FOUND);

    }

    @GetMapping
    //GET at http://localhost:8082/transaction
    public ResponseEntity<List<Transaction>> getAlltransactions() {
        List<Transaction> transactions = null;
        transactions = iTransactionService.getAllTransactions();
        if (transactions != null) {
            return ResponseEntity.ok().body(transactions);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("charity/{id}")
    //GET at http://localhost:8082/transaction/charity
    public ResponseEntity<List<CharityTransactionDTO>> getTransactionsByReceivingBankAccountId(@PathVariable Long id) {
        List<CharityTransactionDTO> charityTransactions = iTransactionService.getTransactionsByReceivingBankAccountId(id);
        if (charityTransactions != null) {
            return ResponseEntity.ok().body(charityTransactions);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("user/{id}")
    //GET at http://localhost:8082/transaction/charity
    public ResponseEntity<List<UserTransactionDTO>> getTransactionsBySendingBankAccountId(@PathVariable Long id) {
        List<UserTransactionDTO> userTransactions = iTransactionService.getTransactionsBySendingBankAccountId(id);
        if (userTransactions != null) {
            return ResponseEntity.ok().body(userTransactions);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    //DELETE at http://localhost:8082/transaction/3
    public ResponseEntity deleteTransaction(@PathVariable long id) {
        iTransactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    //PUT at http://localhost:8082/transaction/
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) {
        if (iTransactionService.updateTransaction(transaction)) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity("Transaction was not updated", HttpStatus.NOT_FOUND);
    }
}
