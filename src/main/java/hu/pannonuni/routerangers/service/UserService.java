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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole().toString())
                        .build())
                .orElseThrow(() -> {
                    String message = String.format("Invalid username or password: %s", username);
                    log.info(message);
                    return new UsernameNotFoundException(message);
                });
    }
}
