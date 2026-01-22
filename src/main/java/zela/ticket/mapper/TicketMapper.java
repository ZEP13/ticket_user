package zela.ticket.mapper;

import zela.ticket.dto.TicketDTO;
import zela.ticket.entity.TicketEntity;

public class TicketMapper {
    public static TicketEntity toEntity(TicketDTO dto) {
        TicketEntity entity = new TicketEntity();

        entity.setId(dto.id());
        entity.setTitre(dto.titre());
        entity.setDescription(dto.description());
        entity.setUser(dto.user_id());
        entity.setDate_creation(dto.date_creation());
        entity.setDate_cloture(dto.date_cloture());

        return entity;
    }

    public static TicketDTO toDto(TicketEntity entity) {
        return new TicketDTO(
                entity.getId(),
                entity.getTitre(),
                entity.getDescription(),
                entity.getUser(),
                entity.getDate_creation(),
                entity.getDate_cloture());
    }
}
