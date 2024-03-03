package com.bank.bankingappbackend;

import com.bank.bankingappbackend.Enum.Role;
import com.bank.bankingappbackend.entity.User;
import com.bank.bankingappbackend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class BankingAppBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankingAppBackendApplication.class, args);
    }


private final UserRepository userRepository;

    public  void run(String... args)
    {
        User adminAccount=userRepository.findByRole(Role.ADMIN);

        if(null==adminAccount)
        {
            User user=new User();
            user.setEmail("admin@gmail.com");
   user.setUserName("Admin");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));

            userRepository.save(user);
        }
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
