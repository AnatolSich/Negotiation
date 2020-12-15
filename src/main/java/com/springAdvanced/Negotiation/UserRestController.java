package com.springAdvanced.Negotiation;


import com.springAdvanced.Negotiation.model.json.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    final
    UserRepository userRepository;
    final
    UserService userService;
    final RequestMappingHandlerMapping handlerMapping;

    public UserRestController(UserRepository userRepository, UserService userService, RequestMappingHandlerMapping handlerMapping) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.handlerMapping = handlerMapping;
    }

    @GetMapping ()
    public List<User> getAllUsers() {
        return userService.getAllUserLimit(2L, "Desc").stream()
                .map(com.springAdvanced.Negotiation.model.db.User::convertToJsonUser)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(new com.springAdvanced.Negotiation.model.db.User()).convertToJsonUser();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) {
        com.springAdvanced.Negotiation.model.db.User savedUser = userRepository.save(user.convertToDBUser());
        return savedUser.convertToJsonUser();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        com.springAdvanced.Negotiation.model.db.User savedUser = userRepository.save(user.convertToDBUser());
        return savedUser.convertToJsonUser();
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User partiallyUpdateUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        com.springAdvanced.Negotiation.model.db.User savedUser = userRepository.save(user.convertToDBUser(userRepository));
        return savedUser.convertToJsonUser();
    }
}
