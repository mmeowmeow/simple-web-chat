package com.example.chat.repositories;

import com.example.chat.rest.models.User;
import com.example.chat.rest.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJdbcTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    public UserRepositoryTest() {
    }

    @Test
    public void findUserByFirstName() {
        User expectedUser = entities();

        User actualUser = userRepository.findUserByFirstName("Winifield");

        assertEquals(expectedUser.getId(), actualUser.getId());
    }

    private User entities() {
        return new User(2L, "Winifield", "Wilderspoon", "wwilderspoon1@spiegel.de", null);
    }
}
