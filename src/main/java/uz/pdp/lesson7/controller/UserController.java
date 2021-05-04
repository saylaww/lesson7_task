package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.RegisterDto;
import uz.pdp.lesson7.payload.UserDto;
import uz.pdp.lesson7.repository.LavozimRepository;
import uz.pdp.lesson7.service.AuthService;
import uz.pdp.lesson7.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    LavozimRepository lavozimRepository;


    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.addUser
                (userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



}
