package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andreoidlnx.company_manager_server.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    List<User> findAll();

    long count();

    boolean existsByUsername(String username);

    User findByUsername( String username);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    User findByPassword(String password);

    User findByCode(String code);

    List<User> findByVisibleTrue(); //findAllVisible

}
