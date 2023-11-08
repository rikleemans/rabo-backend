package rabobankAPI.API.ServiceInterface;

import rabobankAPI.API.Model.BankAccount;

public interface IBankAccountService {
    BankAccount createBankAccount();

    BankAccount getBankAccountByIban(String iban);

    boolean depositMoney(Long id, double depositAmount);

    boolean withdrawMoney(Long id, double withdrawAmount);

    double getBalance(Long id);

    BankAccount existBankAccountById(Long sendingBankAccountId);
}
