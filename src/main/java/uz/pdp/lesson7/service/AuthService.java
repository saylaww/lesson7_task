package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.User;
import uz.pdp.lesson7.exceprions.ResourceNotFoundException;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.RegisterDto;
import uz.pdp.lesson7.repository.LavozimRepository;
import uz.pdp.lesson7.repository.UserRepository;
import uz.pdp.lesson7.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
            return new ApiResponse("Parollar mos emas", false);

        boolean b = userRepository.existsByUsername(registerDto.getUsername());
        if (b)
            return new ApiResponse("Bunday usernmae bazada mavjud", false);

        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                lavozimRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("lavozim","name",AppConstants.USER)),
                true
        );

        userRepository.save(user);

        return new ApiResponse("Muvafaqqiyatli ro'yxatdan o'tdingiz", true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
