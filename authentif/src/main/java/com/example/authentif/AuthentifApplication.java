package com.example.authentif;

import com.example.authentif.Entity.Role;
import com.example.authentif.Entity.User;
import com.example.authentif.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AuthentifApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthentifApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run (UserService userService){
        return args ->{
            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_MAGASINIER"));
            userService.saveRole(new Role(null,"ROLE_CLIENT"));
            userService.saveRole(new Role(null,"ROLE_CAISSIER"));

            userService.saveUser((new User(null,"John Travolta","john@gmail.com","1234",225254561,new ArrayList<>())));
            userService.saveUser((new User(null,"Nurhene Maaouia","nour@gmail.com","5678",2254561,new ArrayList<>())));
            userService.saveUser((new User(null,"sam sam","sam@gmail.com","963",22554561,new ArrayList<>())));
            userService.saveUser((new User(null,"kris Travolta","kris@gmail.com","852",22527561,new ArrayList<>())));


            userService.addRoleToUser("John Travolta","ROLE_SUPER_ADMIN");
            userService.addRoleToUser("Nurhene Maaouia","ROLE_MAGASINIER");
            userService.addRoleToUser("sam sam","ROLE_CAISSIER");
            userService.addRoleToUser("kris Travolta","ROLE_CAISSIER");


        };
    }

}
