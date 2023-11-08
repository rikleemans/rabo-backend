package rabobankAPI.API.DALInterface;

import rabobankAPI.API.Model.Charity;

import java.util.List;

public interface ICharityDAL {

    Charity getCharityById(Long id);

    List<Charity> getAllCharities();

    Charity createCharity(Charity charity);

    void deleteCharity(Long id);

    boolean updateCharity(Charity charity);

    Charity findCharityByBankAccountId(Long receiveId);
}
