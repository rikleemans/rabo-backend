package rabobankAPI.API.MockDAL;

import org.junit.jupiter.api.Test;
import rabobankAPI.API.Model.Transaction;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TransactionMockDALTest {

    private final TransactionMockDAL transactionMockDAL = new TransactionMockDAL();

    @Test
    void getTransactionByIdHappyFlow() {
        //Arrange
        Long expectedId = 1L;

        //Act
        Long actualId = transactionMockDAL.getTransactionById(1L).getId();

        //Assert
        assertEquals(expectedId, actualId);
    }

    @Test
    void getTransactionByIdFailIdIsOutOfBounds() {
        //Arrange
        Transaction expectedTransaction = null;

        //Act
        Transaction actualTransaction = transactionMockDAL.getTransactionById(10L);

        //Assert
        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    void getTransactionByIdFailIdIsNegative() {
        //Arrange
        Transaction expectedTransaction = null;

        //Act
        Transaction actualTransaction = transactionMockDAL.getTransactionById(-1L);

        //Assert
        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    void getTransactionByIdFailIdIsNull() {
        //Arrange
        Transaction expectedTransaction = null;

        //Act
        Transaction actualTransaction = transactionMockDAL.getTransactionById(null);

        //Assert
        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    void getAllTransactionsHappyFlow() {

        //Arrange
        int expectedSize = 3;

        //Act
        int actualSize = transactionMockDAL.getAllTransactions().size();

        //Assert
        assertEquals(expectedSize, actualSize);

    }

    @Test
    void createTransactionHappyFlow() {
        //Arrange
        Transaction transactionToCreate = new Transaction(null, 50, "Test transaction",1L,2L,new Date());
        double expectedAmount = 50;
        String expectedDescription = "Test transaction";
        Long expectedSendingBankAccountId = 1L;
        Long expectedRecievingBankAccountId = 2L;


        //Act
        Transaction createdTransaction = transactionMockDAL.createTransaction(transactionToCreate);

        //Arrange
        assertEquals(expectedAmount, createdTransaction.getAmount());
        assertEquals(expectedDescription, createdTransaction.getDescription());
        assertEquals(expectedSendingBankAccountId, createdTransaction.getSendingBankAccountId());
        assertEquals(expectedRecievingBankAccountId, createdTransaction.getReceivingBankAccountId());
    }

    @Test
    void createTransactionAmountIsNegative() {

        //Arrange
        Transaction transactionToCreate = new Transaction(null, -10, "Test transaction",1L,2L,new Date());

        //Act
        Transaction createdTransaction = transactionMockDAL.createTransaction(transactionToCreate);

        //Arrange
        assertNull(createdTransaction);
    }

    @Test
    void createTransactionAmountIsTooHigh() {

        //Arrange
        Transaction transactionToCreate = new Transaction(null, 10000000, "Test transaction",1L,2L,new Date());

        //Act
        Transaction createdTransaction = transactionMockDAL.createTransaction(transactionToCreate);

        //Arrange
        assertNull(createdTransaction);
    }

    @Test
    void deleteTransactionHappyFlow() {
        //Arrange
        List<Transaction> allTransactions = transactionMockDAL.getAllTransactions();
        int expectedSize = 2;

        //Act
        transactionMockDAL.deleteTransaction(3L);

        //Assert
        assertEquals(expectedSize, allTransactions.size());
    }

    @Test
    void deleteTransactionFailIsTransactionNull() {
        //Arrange
        List<Transaction> allTransactions = transactionMockDAL.getAllTransactions();
        int expectedSize = 3;

        //Act
        transactionMockDAL.deleteTransaction(null);

        //Assert
        assertEquals(expectedSize, allTransactions.size());
    }

    @Test
    void updateTransactionHappyFlow() {
        //Arrange
        Transaction transactionToUpdate = transactionMockDAL.getTransactionById(1L);
        transactionToUpdate.setDescription("New description");
        Long expectedId = 1L;
        double expectedAmount = 11;
        String expectedDescription = "New description";
        Long expectedSendingBankAccountId = 1L;
        Long expectedReceivingBankAccountId = 2L;

        //Act
        transactionMockDAL.updateTransaction(transactionToUpdate);

        Transaction updatedTransaction = transactionMockDAL.getTransactionById(1L);

        //Assert
        assertEquals(expectedId, updatedTransaction.getId());
        assertEquals(expectedAmount, updatedTransaction.getAmount());
        assertEquals(expectedDescription, updatedTransaction.getDescription());
        assertEquals(expectedSendingBankAccountId, updatedTransaction.getSendingBankAccountId());
        assertEquals(expectedReceivingBankAccountId, updatedTransaction.getReceivingBankAccountId());
    }

    @Test
    void getTransactionsByReceivingBankAccountIdHappyFlow() {
        //Arrange
        int expectedSize = 1;

        //Act
        List<Transaction> getResults = transactionMockDAL.getTransactionsByReceivingBankAccountId(2L);
        int resultSize = getResults.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void getTransactionsByReceivingBankAccountIdNotFound() {
        //Arrange
        int expectedSize = 0;

        //Act
        List<Transaction> getResults = transactionMockDAL.getTransactionsByReceivingBankAccountId(1L);
        int resultSize = getResults.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void getTransactionsBySendingBankAccountIdHappyFlow() {
        //Arrange
        int expectedSize = 1;

        //Act
        List<Transaction> getResults = transactionMockDAL.getTransactionsBySendingBankAccountId(3L);
        int resultSize = getResults.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void getTransactionsBySendingBankAccountIdNotFound() {
        //Arrange
        int expectedSize = 0;

        //Act
        List<Transaction> getResults = transactionMockDAL.getTransactionsBySendingBankAccountId(2L);
        int resultSize = getResults.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }
}