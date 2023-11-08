package rabobankAPI.API.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rabobankAPI.API.DALInterface.ITransactionDAL;
import rabobankAPI.API.DTO.CharityTransactionDTO;
import rabobankAPI.API.DTO.TransactionDTO;
import rabobankAPI.API.DTO.UserTransactionDTO;
import rabobankAPI.API.Enum.Account;
import rabobankAPI.API.Logic.convert;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.Model.Transaction;
import rabobankAPI.API.ServiceInterface.IBankAccountService;
import rabobankAPI.API.ServiceInterface.ICharityService;
import rabobankAPI.API.ServiceInterface.ITransactionService;
import rabobankAPI.API.ServiceInterface.IUserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("TransactionService")
public class TransactionService implements ITransactionService {
    private final ITransactionDAL iTransactionDAL;
    private final IBankAccountService iBankAccountService;
    private final convert convert;
    private final ICharityService iCharityService;
    private final IUserService iUserService;

    public TransactionService(@Qualifier("TransactionDAL") ITransactionDAL iTransactionDAL, @Qualifier("BankAccountService") IBankAccountService iBankAccountService, @Qualifier("convert") convert convert, @Qualifier("CharityService") ICharityService iCharityService, @Qualifier("UserService") IUserService iUserService) {
        this.iTransactionDAL = iTransactionDAL;
        this.iBankAccountService = iBankAccountService;
        this.convert = convert;
        this.iCharityService = iCharityService;
        this.iUserService = iUserService;
    }

    public Transaction getTransactionById(Long id) {
        try {
            return iTransactionDAL.getTransactionById(id);
        } catch (Exception e) {
            return iTransactionDAL.getTransactionById(id);
        }
    }

    public List<Transaction> getAllTransactions() {
        try {
            return iTransactionDAL.getAllTransactions();
        } catch (Exception e) {
            return null;
        }
    }

    public List<CharityTransactionDTO> getTransactionsByReceivingBankAccountId(Long bankAccountId) {
        try {
            List<Transaction> results = iTransactionDAL.getTransactionsByReceivingBankAccountId(bankAccountId);

            if (results != null && !results.isEmpty()) {
                return convert.toCharityTransactionDTOList(results);
            }

            return new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<UserTransactionDTO> getTransactionsBySendingBankAccountId(Long bankAccountId) {
        try {
            List<Transaction> results = iTransactionDAL.getTransactionsBySendingBankAccountId(bankAccountId);

            if (results != null && !results.isEmpty()) {
                return toUserTransactionDTOList(results);
            }

            return new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private List<UserTransactionDTO> toUserTransactionDTOList(List<Transaction> transactions) {
        List<UserTransactionDTO> returnResult = new ArrayList<>();
        for (Transaction transaction : transactions) {
            returnResult.add(toUserTransactionDTO(transaction));
        }
        return returnResult;
    }

    private UserTransactionDTO toUserTransactionDTO(Transaction transaction) {
        double amount = transaction.getAmount();
        String description = transaction.getDescription();
        Date date = transaction.getDate();
        Long id = transaction.getReceivingBankAccountId();
        String receiverName = null;
        try {
            receiverName = getReceiverNameByBankAccountId(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new UserTransactionDTO(amount, receiverName, description, date);
    }

    private String getReceiverNameByBankAccountId(Long id) throws IOException {
        Charity charity = iCharityService.getCharityByBankAccountId(id);
        if (charity != null) {
            return charity.getName();
        }
        AppUser user = iUserService.getUserByBankAccountId(id);
        return user.getUsername();
    }

    public Transaction createTransaction(TransactionDTO transactionDTO, Account account) {
        try {
            //check user input not null
            if (transactionDTO.getAmount() <= 0 || transactionDTO.getDescription() == null || transactionDTO.getSendingBackAccountId() <= 0 || transactionDTO.getReceivingBankAccountId() <= 0)
                return null;

            //convert dto to model
            Transaction transaction = convert.toTransaction(transactionDTO);

            if (!validateTransaction(transaction, account))
                return null;

            if (processPayment(transaction))
                // save transaction in db
                return iTransactionDAL.createTransaction(transaction);

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean validateTransaction(Transaction transaction, Account account) {
        Long sendId = transaction.getSendingBankAccountId();
        Long receiveId = transaction.getReceivingBankAccountId();
        double amount = transaction.getAmount();

        // Check sendingId
        BankAccount bankAccount = iBankAccountService.existBankAccountById(sendId);
        if (bankAccount == null)
            return false;

        // Check if receiving is a valid charity or user
        if (account == Account.CHARITY) {
            if (iCharityService.getCharityByBankAccountId(receiveId) == null)
                return false;
        } else {
            if (iUserService.getUserByBankAccountId(receiveId) == null)
                return false;
        }

        // Check sending bank account balance
        return bankAccount.getAmount() >= amount;
    }

    private boolean processPayment(Transaction transaction) {
        try {
            Long sendId = transaction.getSendingBankAccountId();
            Long receiveId = transaction.getReceivingBankAccountId();
            double amount = transaction.getAmount();

            // Withdraw amount of sending bank account
            if (iBankAccountService.withdrawMoney(sendId, amount)) {
                // Deposit amount on receiving bank account
                if (iBankAccountService.depositMoney(receiveId, amount))
                    return true;
                //When failing to deposit to receiver, send money back
                if (restorePayment(sendId, amount))
                    return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean restorePayment(Long sendId, double amount) {
        try {
            //restore actions
            if (iBankAccountService.depositMoney(sendId, amount))
                return true;
        } catch (Exception e) {
            System.out.println("Money could not be transferred back to the sender and is now lost. Amount= " + amount + ", senderid= " + sendId);
            throw e;
        }
        return false;
    }

    public void deleteTransaction(Long id) {
        try {
            iTransactionDAL.deleteTransaction(id);
        } catch (Exception e) {
        }
    }

    public boolean updateTransaction(Transaction transaction) {
        try {
            //check user input not null
            if (transaction.getAmount() > 0 || transaction.getDescription() == null)
                return false;

            //save user info
            iTransactionDAL.updateTransaction(transaction);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
