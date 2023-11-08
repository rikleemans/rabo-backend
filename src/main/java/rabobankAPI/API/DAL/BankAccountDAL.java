package rabobankAPI.API.DAL;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.DALInterface.IBankAccountDAL;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.Repository.IBankAccountRepo;

@Repository("BankAccountDAL")
public class BankAccountDAL implements IBankAccountDAL {
    private final IBankAccountRepo iBankAccountRepo;

    public BankAccountDAL(@Qualifier("BankAccountRepo") IBankAccountRepo iBankAccountRepo) {
        this.iBankAccountRepo = iBankAccountRepo;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return iBankAccountRepo.save(bankAccount);
    }

    public BankAccount getBankAccountByIban(String iban) {
        return iBankAccountRepo.findBankAccountByIban(iban);
    }

    public BankAccount getBankAccountById(Long id) {
        return iBankAccountRepo.findBankAccountById(id);
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        return iBankAccountRepo.save(bankAccount);
    }
}
