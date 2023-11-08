package rabobankAPI.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import rabobankAPI.API.Model.BankAccount;

@Repository("BankAccountRepo")
public interface IBankAccountRepo extends JpaRepository<BankAccount, Long> {
    BankAccount findBankAccountByIban(String iban);
    BankAccount findBankAccountById(Long id);
}
