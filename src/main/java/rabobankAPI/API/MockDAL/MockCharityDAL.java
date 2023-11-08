package rabobankAPI.API.MockDAL;

import rabobankAPI.API.DALInterface.ICharityDAL;
import rabobankAPI.API.Model.Charity;

import java.util.ArrayList;
import java.util.List;

public class MockCharityDAL implements ICharityDAL {
    private List<Charity> charities;

    public MockCharityDAL() {
        this.charities = new ArrayList<>();
        charities.add(new Charity(1L, "www", "desc", "www@www.nl", "link", 1L));
        charities.add(new Charity(2L, "www", "desc", "www@www.nl", "link", 2L));
        charities.add(new Charity(3L, "www", "desc", "www@www.nl", "link", 3L));
        charities.add(new Charity(4L, "www", "desc", "www@www.nl", "link", 4L));
    }

    public Charity getCharityById(Long id) {
        for(Charity charity: charities){
            if(charity.getId().equals(id)){
                return charity;
            }
        }
        return null;
    }

    public List<Charity> getAllCharities() {
        return charities;
    }

    public Charity createCharity(Charity charity) {
        Long id = charities.get(charities.size()-1).getId()+1;
        charity.setId(id);
        charities.add(charity);
        return charity;
    }

    public void deleteCharity(Long id) {
        int indexOfProduct = 0;
        for(Charity charity : charities) {
            if (charity.getId().equals(id)){
                continue;
            }
            indexOfProduct++;
        }
        if(indexOfProduct == charities.size()){
            return;
        }
        charities.remove(indexOfProduct);
    }

    public boolean updateCharity(Charity charity) {
        int indexOfProduct = 0;
        for(Charity charity1 : charities) {
            if (charity1.getId().equals(charity.getId())){
                continue;
            }
            indexOfProduct++;
        }
        if (indexOfProduct == charities.size()){
            return false;
        }
        charities.set(indexOfProduct, charity);
        return true;
    }

    public Charity findCharityByBankAccountId(Long receiveId) {
        for(Charity charity: charities){
            if(charity.getBankAccountId().equals(receiveId)){
                return charity;
            }
        }
        return null;
    }
}
