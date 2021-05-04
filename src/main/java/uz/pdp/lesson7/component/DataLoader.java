package uz.pdp.lesson7.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.lesson7.entity.Lavozim;
import uz.pdp.lesson7.entity.User;
import uz.pdp.lesson7.entity.enums.Huquq;
import uz.pdp.lesson7.repository.LavozimRepository;
import uz.pdp.lesson7.repository.UserRepository;
import uz.pdp.lesson7.utils.AppConstants;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")){
            Huquq[] huquqs = Huquq.values();

            Lavozim admin = lavozimRepository.save(new Lavozim(
                    AppConstants.ADMIN,
                    Arrays.asList(huquqs),
                    "Systema egasi"
            ));

            Lavozim user = lavozimRepository.save(new Lavozim(
                    AppConstants.USER,
                    Arrays.asList(Huquq.ADD_COMMENT, Huquq.EDIT_COMMENT, Huquq.DELETE_MY_COMMENT),
                    "Oddiy foydalanuvchi"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }


    }
}
