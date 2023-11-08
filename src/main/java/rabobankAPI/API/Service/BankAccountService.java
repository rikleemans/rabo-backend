package rabobankAPI.API.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rabobankAPI.API.DALInterface.IBankAccountDAL;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.ServiceInterface.IBankAccountService;

import java.util.Random;

@Service("BankAccountService")
public class BankAccountService implements IBankAccountService {
    private final IBankAccountDAL iBankAccountDAL;

    public BankAccountService(@Qualifier("BankAccountDAL") IBankAccountDAL iBankAccountDAL) {
        this.iBankAccountDAL = iBankAccountDAL;
    }

    String RandomGen(int limit) {
        Random random = new Random();
        int randNum = random.nextInt(limit);
        return String.valueOf(randNum);
    }

    String RandomRepeater(int timesToRepeat, int limit) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < timesToRepeat; i++) {
            str.append(RandomGen(limit));
        }
        return str.toString();
    }

    String IbanRandomizer() {
        return "NL" +
                RandomRepeater(2, 10) +
                "RABO" +
                RandomRepeater(10, 10);
    }

    public BankAccount createBankAccount() {
        try {
            BankAccount bankAccount = new BankAccount(IbanRandomizer(), 5000000);
            return iBankAccountDAL.createBankAccount(bankAccount);
        } catch (Exception e) {
            return null;
        }
    }

    public BankAccount getBankAccountByIban(String iban) {
        try {
            return iBankAccountDAL.getBankAccountByIban(iban);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean depositMoney(Long id, double depositAmount) {
        BankAccount bankAccount = iBankAccountDAL.getBankAccountById(id);
        double oldAmount = bankAccount.getAmount();

        double newAmount = oldAmount + depositAmount;
        bankAccount.setAmount(newAmount);
        iBankAccountDAL.updateBankAccount(bankAccount);
        return true;
    }

    public boolean withdrawMoney(Long id, double withdrawAmount) {
        BankAccount bankAccount = iBankAccountDAL.getBankAccountById(id);
        double oldAmount = bankAccount.getAmount();
        double newAmount = oldAmount - withdrawAmount;

        if (newAmount < 0)
            return false;

        bankAccount.setAmount(newAmount);
        iBankAccountDAL.updateBankAccount(bankAccount);
        return true;
    }

    public double getBalance(Long id) {
        return iBankAccountDAL.getBankAccountById(id).getAmount();
    }

    public BankAccount existBankAccountById(Long sendingBankAccountId) {
        return iBankAccountDAL.getBankAccountById(sendingBankAccountId);
    }
}
