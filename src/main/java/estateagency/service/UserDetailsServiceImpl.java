package estateagency.service;

import estateagency.dao.UserAuthorityRepository;
import estateagency.dao.UserRepository;
import estateagency.model.User;
import estateagency.model.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthorityRepository authorityRepository;

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        user.setAuthorities(getGrantedAuthorities(user));
        return user;
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserAuthority authority : authorityRepository.findByUser(user)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getAuthority().getUserAuthority()));
        }
        return authorities;
    }
}
