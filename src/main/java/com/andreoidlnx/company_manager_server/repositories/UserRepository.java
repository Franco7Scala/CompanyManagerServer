package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.andreoidlnx.company_manager_server.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //void create(User user);

    //void edit(User user);

    //void remove(User user);

    User findById(int id);

    List<User> findAll();

    //List<User> findByRange(int[] range);

    //int count();

    boolean existsByUsername(String username);

    User findByUsername( String username);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    User findByPassword(String password);

    User findByCode(String code);

    //List<User> findAllVisible();

}
