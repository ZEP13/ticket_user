package zela.ticket.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import zela.ticket.dto.UserDTO;
import zela.ticket.service.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @RequestMapping("/create")
    public void createUser(@RequestBody UserDTO userDTO) {
        service.createUser(userDTO);
    }

    @RequestMapping("/current")
    public UserDTO getCurrentUser() {
        Long userId = service.getCurrentUserId();
        return service.getUserById(userId);
    }

}
