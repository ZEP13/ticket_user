package zela.ticket.dto;

import java.time.LocalDateTime;

import zela.ticket.entity.UserEntity;

public record TicketDTO(
        Long id,
        String titre,
        String description,
        UserEntity id_user,
        LocalDateTime date_creation,
        LocalDateTime date_cloture) {

}
