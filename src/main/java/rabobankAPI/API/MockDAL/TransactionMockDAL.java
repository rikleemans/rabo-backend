package rabobankAPI.API.MockDAL;

import org.springframework.stereotype.Repository;
import rabobankAPI.API.DALInterface.ITransactionDAL;
import rabobankAPI.API.Model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository("TransactionMockDAL")
public class TransactionMockDAL implements ITransactionDAL {

    private List<Transaction> transactions = new ArrayList<>();

    public TransactionMockDAL() {
        transactions.add(new Transaction(1L,11, "Test1", 1L, 2L, new Date()));
        transactions.add(new Transaction(2L,100, "Test2", 3L, 4L, new Date()));
        transactions.add(new Transaction(3L,5000, "Test3", 4L, 5L, new Date()));
    }

    public Transaction getTransactionById(Long id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction createTransaction(Transaction transaction) {
        if(transaction.getAmount() <= 0 || transaction.getAmount() > 5000000){
            return null;
        } else {
            Long newId = transactions.get(transactions.size() - 1).getId() + 1;
            transaction.setId(newId);
            transactions.add(transaction);
            return transaction;
        }
    }

    public void deleteTransaction(Long id) {
        int indexer = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                break;
            }
            indexer++;
        }
        if (indexer >= transactions.size()) {
            return;
        }
        transactions.remove(indexer);
    }

    public boolean updateTransaction(Transaction newTransaction) {
        int indexer = 0;
        for (Transaction transaction : transactions) {
            if (Objects.equals(transaction.getId(), newTransaction.getId())) {
                break;
            }
            indexer++;
            transactions.set(indexer, newTransaction);
        }
        return true;
    }

    @Override
    public List<Transaction> getTransactionsByReceivingBankAccountId(Long bankAccountId) {
        List<Transaction> results = new ArrayList<>();
            for (Transaction transaction : transactions){
                if(transaction.getReceivingBankAccountId().equals(bankAccountId)){
                    results.add(transaction);
                }
            }
        return results;
    }

    @Override
    public List<Transaction> getTransactionsBySendingBankAccountId(Long bankAccountId) {
        List<Transaction> results = new ArrayList<>();
        for (Transaction transaction : transactions){
            if(transaction.getSendingBankAccountId().equals(bankAccountId)){
                results.add(transaction);
            }
        }
        return results;
    }
}
