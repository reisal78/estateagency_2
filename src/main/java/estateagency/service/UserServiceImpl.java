package estateagency.service;

import estateagency.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int getCountUsersByLogin(String username) {
        return userRepository.findByUsername(username) == null ? 0 : 1;
    }
}
