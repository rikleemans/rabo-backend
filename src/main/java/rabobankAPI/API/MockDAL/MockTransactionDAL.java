package rabobankAPI.API.MockDAL;

import rabobankAPI.API.DALInterface.ITransactionDAL;
import rabobankAPI.API.Model.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MockTransactionDAL implements ITransactionDAL {
    private List<Transaction> transactions;

    public MockTransactionDAL() {
        this.transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, 25, "test", 1L, 2L,new Date(10-03-2015)));
        transactions.add(new Transaction(2L, 25, "test", 1L, 2L,new Date(10-03-2015)));
        transactions.add(new Transaction(3L, 25, "test", 1L, 2L,new Date(10-03-2015)));
        transactions.add(new Transaction(4L, 25, "test", 1L, 2L,new Date(10-03-2015)));
    }


    public Transaction getTransactionById(Long id) {
        for(Transaction transaction: transactions){
            if(transaction.getId().equals(id)){
                return transaction;
            }
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction createTransaction(Transaction transaction) {
        Long id = transactions.get(transactions.size()-1).getId()+1;
        transaction.setId(id);
        transactions.add(transaction);
        return transaction;
    }

    public void deleteTransaction(Long id) {
    }

    public boolean updateTransaction(Transaction transaction) {
        return false;
    }

    public List<Transaction> getTransactionsByReceivingBankAccountId(Long bankAccountId) {
        for(Transaction transaction: transactions){
            if(transaction.getReceivingBankAccountId().equals(bankAccountId)){
                return Collections.singletonList(transaction);
            }
        }
        return null;
    }

    public List<Transaction> getTransactionsBySendingBankAccountId(Long bankAccountId) {
        for(Transaction transaction: transactions){
            if(transaction.getSendingBankAccountId().equals(bankAccountId)){
                return Collections.singletonList(transaction);
            }
        }
        return null;
    }
}
