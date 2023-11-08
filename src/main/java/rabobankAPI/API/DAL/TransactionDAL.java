package rabobankAPI.API.DAL;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.DALInterface.ITransactionDAL;
import rabobankAPI.API.Model.Transaction;
import rabobankAPI.API.Repository.ITransactionRepo;

import java.util.List;

@Repository("TransactionDAL")
public class TransactionDAL implements ITransactionDAL {

    private final ITransactionRepo iTransactionRepo;

    public TransactionDAL(@Qualifier("TransactionRepo") ITransactionRepo iTransactionRepo) {
        this.iTransactionRepo = iTransactionRepo;
    }

    public Transaction getTransactionById(Long id) {
        return iTransactionRepo.getTransactionById(id);
    }

    public List<Transaction> getAllTransactions() {
        return iTransactionRepo.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {
        return iTransactionRepo.save(transaction);
    }

    public void deleteTransaction(Long id) {
        iTransactionRepo.deleteById(id);
    }

    public boolean updateTransaction(Transaction transaction) {
        iTransactionRepo.save(transaction);
        return true;
    }

    public List<Transaction> getTransactionsByReceivingBankAccountId(Long bankAccountId) {
        return iTransactionRepo.findTransactionsByReceivingBankAccountId(bankAccountId);
    }

    public List<Transaction> getTransactionsBySendingBankAccountId(Long bankAccountId) {
        return iTransactionRepo.findTransactionsBySendingBankAccountId(bankAccountId);
    }
}
