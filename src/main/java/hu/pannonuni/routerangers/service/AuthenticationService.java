package hu.pannonuni.routerangers.service;

import hu.pannonuni.model.UserLoginModel;
import hu.pannonuni.model.UserModel;
import hu.pannonuni.model.UserRegistrationModel;
import hu.pannonuni.routerangers.entity.user.User;
import hu.pannonuni.routerangers.persistence.UserRepository;
import hu.pannonuni.routerangers.util.Blacklist;
import hu.pannonuni.routerangers.value.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserModel register(UserRegistrationModel userRegistrationModel) {
        User user = getUserFromUserRegistrationModel(userRegistrationModel);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        UserModel userModel = getUserModelFromUser(user);
        userModel.setToken(jwtToken);
        return userModel;
    }

    private UserModel getUserModelFromUser(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        userModel.setRole(user.getRole().toString());
        return userModel;
    }

    private User getUserFromUserRegistrationModel(UserRegistrationModel userRegistrationModel) {
        User user = new User();
        user.setEmail(userRegistrationModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationModel.getPassword()));
        user.setRole(Role.USER);
        return user;
    }

    public UserModel login(UserLoginModel userLoginModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginModel.getEmail(),
                        userLoginModel.getPassword()
                )
        );
        var user = userRepository.findByEmail(userLoginModel.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        UserModel userModel = getUserModelFromUser(user);
        userModel.setToken(jwtToken);
        return userModel;
    }

    public void logout(String token){
        Blacklist.addTokenToBlacklist(token);
    }
}
