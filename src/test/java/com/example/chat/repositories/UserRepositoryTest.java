package com.example.chat.repositories;

import com.example.chat.rest.models.User;
import com.example.chat.rest.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@DataJdbcTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {
    
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindUserByFirstName() {
        User foundUser = userRepository.findUserByFirstName("Winifield");
        assertThat(foundUser.getFirstName()).isEqualTo("Winifield");
    }

    @Test
    public void testFindUserByFirstNameNotFound() {
        User foundUser = userRepository.findUserByFirstName("NonExistentUser");
        assertThat(foundUser).isNull();
    }

}
