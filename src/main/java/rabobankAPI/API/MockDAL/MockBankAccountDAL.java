package rabobankAPI.API.MockDAL;

import rabobankAPI.API.DALInterface.IBankAccountDAL;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class MockBankAccountDAL implements IBankAccountDAL {

    private List<BankAccount> bankAccounts;

    public MockBankAccountDAL() {
        this.bankAccounts = new ArrayList<>();
        bankAccounts.add(new BankAccount(1L, "10NLRABO1111111111", 25));
        bankAccounts.add(new BankAccount(2L, "10NLRABO2222222222", 30));
        bankAccounts.add(new BankAccount(3L, "10NLRABO3333333333", 35));
        bankAccounts.add(new BankAccount(4L, "10NLRABO4444444444", 40));
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        Long id = bankAccounts.get(bankAccounts.size()-1).getId()+1;
        bankAccount.setId(id);
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public BankAccount getBankAccountByIban(String iban) {
        for(BankAccount bankAccount: bankAccounts){
            if(bankAccount.getIban().equals(iban)){
                return bankAccount;
            }
        }
        return null;
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        int indexOfProduct = 0;
        for(BankAccount bankAccount1 : bankAccounts) {
            if (bankAccount1.getId().equals(bankAccount.getId())){
                continue;
            }
            indexOfProduct++;
        }
        if (indexOfProduct == bankAccounts.size()){
            return null;
        }
        bankAccounts.set(indexOfProduct, bankAccount);
        return bankAccount;
    }

    public BankAccount getBankAccountById(Long id) {
        for(BankAccount bankAccount: bankAccounts){
            if(bankAccount.getId().equals(id)){
                return bankAccount;
            }
        }
        return null;
    }
}
