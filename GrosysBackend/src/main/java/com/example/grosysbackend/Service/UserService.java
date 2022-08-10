package com.example.grosysbackend.Service;


import com.example.grosysbackend.Entity.Role;
import com.example.grosysbackend.Entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole (Role role);
    void addRoleToUser (String fullName, String roleName);
    User getUser (String fullName);
    List<User> getUsers ();
}
