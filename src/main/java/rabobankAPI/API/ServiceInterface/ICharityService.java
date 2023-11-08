package rabobankAPI.API.ServiceInterface;

import rabobankAPI.API.DTO.CharityDTO;
import rabobankAPI.API.Model.Charity;

import java.util.List;

public interface ICharityService {

    Charity getCharityById(Long id);

    List<Charity> getAllCharities();

    Charity createCharity(CharityDTO charityDTO);

    void deleteCharity(Long id);

    boolean updateCharity(Charity charity);

    Charity getCharityByBankAccountId(Long receiveId);
}
