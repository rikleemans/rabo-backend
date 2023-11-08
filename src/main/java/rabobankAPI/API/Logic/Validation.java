package rabobankAPI.API.Logic;

import org.springframework.stereotype.Service;
import rabobankAPI.API.Model.AppUser;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.Model.Charity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("Validation")
public class Validation {
    private Pattern regexPattern;
    private Matcher regMatcher;

    public Validation() {
    }

    public boolean appUser(AppUser user) {
        return bankAccountId(user.getBankAccountId()) && username(user.getUsername()) && password(user.getPassword());
    }

    public boolean charity(Charity charity) {
        //check bankAccountId
        if (!bankAccountId(charity.getBankAccountId()))
            return false;
        //check name
        return username(charity.getName());
    }

    private boolean password(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!&*_=+?]).{8,}$";
        regexPattern = Pattern.compile(regex);
        regMatcher = regexPattern.matcher(password);
        return regMatcher.matches();
    }

    private boolean username(String username) {
        regexPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?!.*[@#$%!^&*()_=+{}';:/?.>,<])(?=\\S+$).{1,30}$");
        regMatcher = regexPattern.matcher(username);
        return regMatcher.matches();
    }

    private boolean bankAccountId(Long bankAccountId) {
        return true;
    }

}
