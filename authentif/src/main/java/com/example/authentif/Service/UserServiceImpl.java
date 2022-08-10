package com.example.authentif.Service;

import com.example.authentif.Entity.Role;
import com.example.authentif.Entity.User;
import com.example.authentif.Repository.RoleRepo;
import com.example.authentif.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService ,UserDetailsService{
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByFullName(username);

        if(user == null){
            log.error("User not found in the dataBase");
            throw  new UsernameNotFoundException("User not found in the dataBase");
        }else {
            log.info("User  found in the dataBase: {}",username);

        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getFullName(),user.getPassword(),authorities);
    }
    @Override
    public User saveUser(User user) {
        log.info("Saving new User {} to the database ",user.getFullName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new Role {} to the database ",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String fullName, String roleName) {
        log.info("Adding role {} to user {}  ",roleName,fullName);
        User user = userRepo.findByFullName(fullName);

        Role role = roleRepo.findByName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public User getUser(String fullName) {
        log.info("fetching User {} ",fullName);

        return userRepo.findByFullName(fullName);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all the Users {}  ");
        return userRepo.findAll();
    }


}
