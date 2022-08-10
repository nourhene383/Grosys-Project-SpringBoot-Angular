package com.example.authentif.Repository;

import com.example.authentif.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo  extends JpaRepository<User,Long> {
    /*@Query("select u from User u where u.fullName=:fullName")
    List<User> findByFullName(@Param("fullName")String fullName);*/
    User findByFullName(String fullname);
}
