package rabobankAPI.API.DAL;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.DALInterface.ICharityDAL;
import rabobankAPI.API.Model.Charity;
import rabobankAPI.API.Repository.ICharityRepo;

import java.util.List;

@Repository("CharityDAL")
public class CharityDAL implements ICharityDAL {

    private final ICharityRepo iCharityRepo;

    public CharityDAL(@Qualifier("CharityRepo") ICharityRepo iCharityRepo) {
        this.iCharityRepo = iCharityRepo;
    }

    public Charity getCharityById(Long id) {
        return iCharityRepo.findCharityById(id);
    }

    public List<Charity> getAllCharities() {
        return iCharityRepo.findAll();
    }

    public Charity createCharity(Charity charity) {
        return iCharityRepo.save(charity);
    }

    public void deleteCharity(Long id) {
        iCharityRepo.deleteById(id);
    }

    public boolean updateCharity(Charity charity) {
        iCharityRepo.save(charity);
        return true;
    }

    public Charity findCharityByBankAccountId(Long receiveId) {
        return iCharityRepo.findCharityByBankAccountId(receiveId);
    }
}
