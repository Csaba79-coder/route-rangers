package hu.pannonuni.routerangers.service;

import hu.pannonuni.model.UserModel;
import hu.pannonuni.model.UserRegistrationModel;
import hu.pannonuni.routerangers.persistence.UserRepository;
import hu.pannonuni.routerangers.value.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import hu.pannonuni.routerangers.entity.user.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final  JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserModel register(UserRegistrationModel registrationModel) {
        User user = new User();
        user.setEmail(registrationModel.getEmail());
        user.setPassword(passwordEncoder.encode(registrationModel.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        UserModel userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setId(user.getId());
        userModel.setRole(user.getRole().toString());
        userModel.setToken(jwtToken);
        return userModel;
    }

}
