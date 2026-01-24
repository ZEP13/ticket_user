package zela.ticket.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zela.ticket.dto.UserDTO;
import zela.ticket.entity.UserEntity;
import zela.ticket.exception.UserExceptions.UserAlreadyExistsException;
import zela.ticket.exception.UserExceptions.UserIdNotFoundException;
import zela.ticket.mapper.UserMapper;
import zela.ticket.repository.UserRepo;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo repo;

    private PasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = repo.findByNom(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

        UserDTO user = UserMapper.toDto(userEntity);
        return User.builder()
                .username(user.nom())
                .password(user.password())
                .build();
    }

    public void createUser(UserDTO userDTO) {
        if (repo.existsByNom(userDTO.nom())) {
            throw new UserAlreadyExistsException("Username already exists: " + userDTO.nom());
        }

        UserEntity newUser = UserEntity.builder()
                .nom(userDTO.nom())
                .password(bCryptPasswordEncoder.encode(userDTO.password()))
                .build();

        repo.save(newUser);

    }

    public UserDTO getUserById(Long id) {
        UserEntity userEntity = repo.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException("User ID not found: " + id));
        return UserMapper.toDto(userEntity);
    }

    public Long getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserIdNotFoundException("User not authenticated");
        }

        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();

        return repo.findByNom(username)
                .orElseThrow(() -> new UserIdNotFoundException("User not found"))
                .getId();
    }

    public UserEntity getCurrentUserEntity() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserIdNotFoundException("User not authenticated");
        }

        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();

        return repo.findByNom(username)
                .orElseThrow(() -> new UserIdNotFoundException("User not found"));
    }

}
