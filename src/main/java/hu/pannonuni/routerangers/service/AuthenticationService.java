package hu.pannonuni.routerangers.service;

import hu.pannonuni.model.*;
import hu.pannonuni.routerangers.controller.exception.EntityAlreadyExistsException;
import hu.pannonuni.routerangers.entity.user.User;
import hu.pannonuni.routerangers.persistence.UserRepository;
import hu.pannonuni.routerangers.util.Blacklist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.InputMismatchException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserRegisterResponseModel register(UserRegistrationModel userRegistrationModel) {
        checkTwoPasswordEquality(userRegistrationModel.getPassword(), userRegistrationModel.getRepeatPassword());
        checkExistUserWithThisEmail(userRegistrationModel.getEmail());
        User user = getUserFromUserRegistrationModel(userRegistrationModel);
        userRepository.save(user);

        UserRegisterResponseModel userRegisterResponseModel = getUserUserRegisterResponseModelFromUser(user);
        return userRegisterResponseModel;
    }

    private void checkTwoPasswordEquality(String password, String repeatPassword) {
        if(!password.equals(repeatPassword)){
            throw new InputMismatchException(String.format("The password and the repeat password is not equal"));
        }
    }

    private void checkExistUserWithThisEmail(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new EntityAlreadyExistsException(String.format("The user already exists with this email: %s", email));
        }
    }

    private UserRegisterResponseModel getUserUserRegisterResponseModelFromUser(User user) {
        UserRegisterResponseModel userRegisterResponseModel = new UserRegisterResponseModel();
        userRegisterResponseModel.setId(user.getId());
        userRegisterResponseModel.setEmail(user.getEmail());
        userRegisterResponseModel.setRole(user.getRole());
        return userRegisterResponseModel;
    }

    private UserModel getUserModelFromUser(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        userModel.setRole(user.getRole());
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
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginModel.getEmail(),
                            userLoginModel.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InputMismatchException("Invalid email or password");
        }
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
