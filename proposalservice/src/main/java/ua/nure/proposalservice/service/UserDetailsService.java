package ua.nure.proposalservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.nure.proposalservice.dao.UserRepository;
import ua.nure.proposalservice.model.User;

import java.util.Collection;

@Service
public class UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
//                        user.getRoles().stream()
//                        .map(role -> new SimpleGrantedAuthority(""))
//                        .toList();
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return user.getLogin();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
