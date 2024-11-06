package com.smart_ski_rent_ver1_2.security;

import com.smart_ski_rent_ver1_2.security.dto.UserDto;
import com.smart_ski_rent_ver1_2.security.entity.User;
import com.smart_ski_rent_ver1_2.security.service.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@ContextConfiguration(classes = UserMapper.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestMapping {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testUserToUserDtoMapping() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setLogin("jan.kowalski");
        //user.setPassword("haslo123");

        UserDto userDto = userMapper.toUserDto(user);

        // Sprawdź, czy pola są poprawnie mapowane
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getLogin(), userDto.getLogin());
        //assertNull(userDto.getPassword()); // Hasło powinno być pominięte
    }

}
