package rabobankAPI.API.DALInterface;

import rabobankAPI.API.Model.Transaction;

import java.util.List;

public interface ITransactionDAL {

    Transaction getTransactionById(Long id);

    List<Transaction> getAllTransactions();

    Transaction createTransaction(Transaction transaction);

    void deleteTransaction(Long id);

    boolean updateTransaction(Transaction transaction);

    List<Transaction> getTransactionsByReceivingBankAccountId(Long bankAccountId);

    List<Transaction> getTransactionsBySendingBankAccountId(Long bankAccountId);
}
