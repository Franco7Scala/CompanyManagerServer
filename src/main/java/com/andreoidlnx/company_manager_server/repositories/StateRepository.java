package com.andreoidlnx.company_manager_server.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.andreoidlnx.company_manager_server.entities.State;

public interface StateRepository extends JpaRepository<State, String>{
    
    Optional<State> findById(String id);

    List<State> findByName(String name);

    @Query("SELECT s FROM State s WHERE s.favourite = 1")
    List<State> findPreferred();

    @Query("SELECT s FROM State s WHERE s.visible = 1")
    List<State> findAllVisible();

    @Query("SELECT s FROM State s WHERE s.visible = 1 AND s.downloadable = 1")
    List<State> findAllDownloadable();

    @Query("SELECT s FROM State s WHERE s.visible = 1 AND s.downloadable = 0")
    List<State> findAllUnownloadadble();

    List<State> findAll();

    List<State> findByStateBetween(int[] range);

    long count();
    
}
