package zela.ticket.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zela.ticket.dto.UserDTO;

@Service
@AllArgsConstructor
public class UserService {

    public UserDTO createUser(UserDTO userDTO) {
        // Logic to create a user would go here
        return userDTO; // Placeholder return

    }
}
