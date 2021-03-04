package com.fontys.individual.songifybackend.repository;

import com.fontys.individual.songifybackend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPasshash(String username, String passhash);


}
