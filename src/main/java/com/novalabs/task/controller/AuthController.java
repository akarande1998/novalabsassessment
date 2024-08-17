package com.novalabs.task.controller;

import com.novalabs.task.config.TokenProvider;
import com.novalabs.task.model.User;
import com.novalabs.task.model.request.LoginRequest;
import com.novalabs.task.model.request.NewTaskRequest;
import com.novalabs.task.model.request.NewUserRequest;
import com.novalabs.task.model.response.EntityResponse;
import com.novalabs.task.model.response.LoginResponse;
import com.novalabs.task.repository.UserRepository;
import com.novalabs.task.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private TokenProvider jwtTokenUtil;
    //create new user
    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody NewUserRequest newUserRequest){
        return new ResponseEntity<>(iUserService.createNewUser(newUserRequest), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            final Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            final UserDetails candidateDetails = iUserService.loadUserByUsername(loginRequest.getEmail());
            User user = iUserService.findByEmail(candidateDetails.getUsername());
            
            String token = "";
            try {
                token = jwtTokenUtil.generateToken(authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setUserId(user.getId());

           return new ResponseEntity<>(new EntityResponse(loginResponse, 0), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new EntityResponse(e.getMessage(), -1), HttpStatus.UNAUTHORIZED);
        }
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            // throw new Exception("INVALID_CREDENTIALS", e);
            throw new Exception("Please Check Username and Password", e);
        }catch (Exception e) {
            throw new Exception("Please Check Username and Password", e);
        }
    }
}
