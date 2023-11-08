package rabobankAPI.API.ServiceInterface;

import rabobankAPI.API.DTO.CharityTransactionDTO;
import rabobankAPI.API.DTO.TransactionDTO;
import rabobankAPI.API.DTO.UserTransactionDTO;
import rabobankAPI.API.Enum.Account;
import rabobankAPI.API.Model.Transaction;

import java.util.List;

public interface ITransactionService {
    List<CharityTransactionDTO> getTransactionsByReceivingBankAccountId(Long bankAccountId);

    List<UserTransactionDTO> getTransactionsBySendingBankAccountId(Long bankAccountId);

    Transaction getTransactionById(Long id);

    List<Transaction> getAllTransactions();

    Transaction createTransaction(TransactionDTO transactionDTO, Account account);

    void deleteTransaction(Long id);

    boolean updateTransaction(Transaction transaction);
}
