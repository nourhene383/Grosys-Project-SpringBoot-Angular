package com.example.grosysbackend.Repository;

import com.example.grosysbackend.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepo  extends JpaRepository<Role,Long> {
    /*@Query("select r from Role r where r.name=:name")
    List<Role> findByName(@Param("name")String name);*/
    Role findByName(String name);
}
