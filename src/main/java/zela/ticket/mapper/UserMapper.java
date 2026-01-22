package zela.ticket.mapper;

import zela.ticket.dto.UserDTO;
import zela.ticket.entity.UserEntity;

public class UserMapper {
    public static UserDTO toDto(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getNom(),
                entity.getPassword());
    }

    public static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();

        entity.setId(dto.id());
        entity.setNom(dto.nom());
        entity.setPassword(dto.password());

        return entity;
    }
}
