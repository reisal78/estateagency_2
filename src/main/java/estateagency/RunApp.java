package estateagency;

import estateagency.dao.*;
import estateagency.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("estateagency")
@EnableScheduling
public class RunApp {

    /**
     * Запуск программы
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class);
    }

    /**
     * Автоматически запускается после инициализации всех библиотек
     * @return
     */
    @Bean
    public CommandLineRunner init(
            UserRepository userRepository,
            UserAuthorityRepository authorityRepository,
            PasswordEncoder passwordEncoder) {
        return (args) -> {

            //Если в базе данных нет админа, то создаю его
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setEnabled(true);
                userRepository.save(admin);
                UserAuthority authority = new UserAuthority(Authority.ADMIN, admin);
                authorityRepository.save(authority);
            }
        };
    }

}
