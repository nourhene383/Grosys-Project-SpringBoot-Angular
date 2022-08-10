package com.example.authentif.Service;

import com.example.authentif.Entity.Role;
import com.example.authentif.Entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole (Role role);
    void addRoleToUser (String fullName, String roleName);
    User getUser (String fullName);
    List<User> getUsers ();
}
