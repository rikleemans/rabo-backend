package rabobankAPI.API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rabobankAPI.API.Model.Charity;

@Repository("CharityRepo")
public interface ICharityRepo extends JpaRepository<Charity, Long> {
    Charity findCharityById(Long id);
    Charity findCharityByBankAccountId(Long id);
}
