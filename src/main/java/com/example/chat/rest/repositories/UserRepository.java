package com.example.chat.rest.repositories;

import com.example.chat.rest.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByFirstName(String userName);
}
