package exercise.controller;

import exercise.dto.UserDto;
import exercise.model.User;
import exercise.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // Кодировщик BCrypt
    // Используйте для хеширования пароля
    private final PasswordEncoder encoder;

    @GetMapping(path = "")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    // BEGIN
    @PostMapping(path="")
    public Optional<UserDto> login(@RequestBody UserDto userDto) {
        var user =  userRepository.findUserByUsername(userDto.username());
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setUsername(userDto.username());
            newUser.setPassword(encoder.encode(userDto.password()));
            userRepository.save(newUser);
        }
        return Optional.of(userDto);

    }
    // END
}

