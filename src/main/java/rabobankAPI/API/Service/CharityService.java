package rabobankAPI.API.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rabobankAPI.API.DALInterface.ICharityDAL;
import rabobankAPI.API.DTO.CharityDTO;
import rabobankAPI.API.Logic.Validation;
import rabobankAPI.API.Logic.convert;
import rabobankAPI.API.Model.BankAccount;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.ServiceInterface.IBankAccountService;
import rabobankAPI.API.ServiceInterface.ICharityService;

import java.util.List;

@Service("CharityService")
public class CharityService implements ICharityService {
    private final ICharityDAL iCharityDAL;
    private final IBankAccountService iBankAccountService;
    private final Validation validation;
    private final convert convert;

    public CharityService(@Qualifier("CharityDAL") ICharityDAL iCharityDAL, @Qualifier("BankAccountService") IBankAccountService iBankAccountService, @Qualifier("Validation") Validation validation, @Qualifier("convert") convert convert) {
        this.iCharityDAL = iCharityDAL;
        this.iBankAccountService = iBankAccountService;
        this.validation = validation;
        this.convert = convert;
    }

    public Charity getCharityById(Long id) {
        try {
            return iCharityDAL.getCharityById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Charity> getAllCharities() {
        try {
            return iCharityDAL.getAllCharities();
        } catch (Exception e) {
            return null;
        }
    }

    public Charity createCharity(CharityDTO charityDTO) {
        try {
            //check user input not null
            if (charityDTO.getEmail() == null || charityDTO.getName() == null || charityDTO.getDescription() == null)
                return null;

            //convert dto to model
            Charity charity = convert.toCharity(charityDTO);

            //add bankaccount
            BankAccount bankAccount = iBankAccountService.createBankAccount();
            charity.setBankAccountId(bankAccount.getId());

            //check on valid fields
            if (!validation.charity(charity))
                return null;

            //save user info
            return iCharityDAL.createCharity(charity);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteCharity(Long id) {
        try {
            iCharityDAL.deleteCharity(id);
        } catch (Exception e) {
        }
    }

    public boolean updateCharity(Charity charity) {
        try {
            //check user input not null
            if (charity.getEmail() == null || charity.getName() == null || charity.getDescription() == null)
                return false;

            //check on valid fields
            if (!validation.charity(charity))
                return false;

            //save user info
            iCharityDAL.updateCharity(charity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Charity getCharityByBankAccountId(Long receiveId) {
        return iCharityDAL.findCharityByBankAccountId(receiveId);
    }
}
