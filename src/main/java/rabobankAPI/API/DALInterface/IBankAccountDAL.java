package rabobankAPI.API.DALInterface;

import rabobankAPI.API.Model.BankAccount;

public interface IBankAccountDAL {

    BankAccount createBankAccount(BankAccount bankAccount);

    BankAccount getBankAccountByIban(String iban);

    BankAccount updateBankAccount(BankAccount bankAccount);

    BankAccount getBankAccountById(Long id);

}
