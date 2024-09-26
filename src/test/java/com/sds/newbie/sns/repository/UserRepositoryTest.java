package com.sds.newbie.sns.repository;

import com.sds.newbie.sns.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert() {
        userRepository.save(new User(null, "user", "{noop}pass", false));
        var result = userRepository.findAll();

        assertEquals(1, result.size());
    }
}
