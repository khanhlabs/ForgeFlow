package com.example.backend.common.utils;

import com.example.backend.users.entities.User;
import com.example.backend.users.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class UserSeedUtilsTest {

    @Test
    void inserts25UsersWithEncodedPasswords() {
        UserRepository repository = mock(UserRepository.class);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "test-password";
        when(repository.saveAll(any())).thenAnswer(invocation -> {
            List<User> users = invocation.getArgument(0);
            assertEquals(25, users.size());
            assertEquals(25, users.stream().map(User::getEmail).distinct().count());
            assertEquals(25, users.stream().map(User::getFullName).distinct().count());
            assertEquals("Pham Gia Khanh", users.getFirst().getFullName());
            assertEquals("khanhdev206@gmail.com", users.getFirst().getEmail());
            assertEquals("Vo Ngoc Mai", users.getLast().getFullName());
            assertEquals("ngoc.mai24@example.com", users.getLast().getEmail());
            assertTrue(users.stream().allMatch(user -> encoder.matches(rawPassword, user.getPasswordHash())));
            assertFalse(users.stream().anyMatch(user -> rawPassword.equals(user.getPasswordHash())));
            return users;
        });

        new UserSeedUtils(repository, encoder).insert25Users(rawPassword);

        verify(repository).saveAll(any());
    }

    @Test
    void rejectsBlankPassword() {
        UserRepository repository = mock(UserRepository.class);
        UserSeedUtils utils = new UserSeedUtils(repository, new BCryptPasswordEncoder());

        assertThrows(IllegalArgumentException.class, () -> utils.insert25Users(" "));

        verifyNoInteractions(repository);
    }
}
