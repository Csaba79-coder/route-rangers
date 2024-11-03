package hu.pannonuni.routerangers.service;

import hu.pannonuni.routerangers.entity.user.User;
import hu.pannonuni.routerangers.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> {
                    String message = String.format("User not found with email: %s", email);
                    log.info(message);
                    return new UsernameNotFoundException(message);
                }
        );
    }


}
