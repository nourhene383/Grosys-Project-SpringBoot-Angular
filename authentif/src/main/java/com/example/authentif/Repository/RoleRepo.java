package com.example.authentif.Repository;

import com.example.authentif.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RoleRepo  extends JpaRepository<Role,Long> {
    /*@Query("select r from Role r where r.name=:name")
    List<Role> findByName(@Param("name")String name);*/
    Role findByName(String name);
}
