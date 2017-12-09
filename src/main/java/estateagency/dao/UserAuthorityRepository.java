package estateagency.dao;

import estateagency.model.User;
import estateagency.model.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
    List<UserAuthority> findByUser(User user);
}
