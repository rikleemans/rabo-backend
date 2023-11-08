package rabobankAPI.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.Model.Transaction;
import java.util.List;

@Repository("TransactionRepo")
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {
    //These methods were added because the auto generated ones did not work for these circumstances.
    List<Transaction> findTransactionsByReceivingBankAccountId(Long receivingId);
    Transaction getTransactionById(Long id);
    List<Transaction> findTransactionsBySendingBankAccountId(Long sendingId);
}
