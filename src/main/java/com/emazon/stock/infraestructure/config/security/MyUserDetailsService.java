package com.emazon.stock.infraestructure.config.security;



import com.emazon.stock.domain.model.User;
import com.emazon.stock.domain.puertos.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Optional<User> user = userRepositoryPort.findUserByToken("Bearer " + token);

        if (user.isPresent()) {
            User userObj = user.get();
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .authorities(userObj.getRole().getName())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();

            return userDetails;
        } else {
            throw new UsernameNotFoundException(token);
        }
    }


}
