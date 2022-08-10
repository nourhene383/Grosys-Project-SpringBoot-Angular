package com.example.grosysbackend.Repository;

import com.example.grosysbackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {
    /*@Query("select u from User u where u.fullName=:fullName")
    List<User> findByFullName(@Param("fullName")String fullName);*/
    User findByFullName(String fullname);
}
