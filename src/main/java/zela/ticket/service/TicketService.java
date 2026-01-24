package zela.ticket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import zela.ticket.dto.TicketDTO;
import zela.ticket.entity.TicketEntity;
import zela.ticket.entity.UserEntity;
import zela.ticket.exception.TicketExceptions.TicketNotFoundException;
import zela.ticket.mapper.TicketMapper;
import zela.ticket.repository.TicketRepo;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepo repo;
    private final UserService userService;

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        UserEntity currentUser = userService.getCurrentUserEntity();
        TicketEntity entity = new TicketEntity();
        entity.setTitre(ticketDTO.titre());
        entity.setDescription(ticketDTO.description());
        entity.setUser(currentUser);
        entity.setDate_creation(LocalDateTime.now());

        TicketEntity savedEntity = repo.save(entity);
        return TicketMapper.toDto(savedEntity);
    }

    public TicketDTO getTicketById(Long id) {
        TicketEntity ticketEntity = repo.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));
        return TicketMapper.toDto(ticketEntity);
    }

    public void deleteTicket(Long id) {
        if (!repo.existsById(id)) {
            throw new TicketNotFoundException("Ticket not found with id: " + id);
        }
        repo.deleteById(id);
    }

    public TicketDTO endTicket(Long id) {
        TicketEntity ticketEntity = repo.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));
        ticketEntity.setDate_cloture(LocalDateTime.now());

        Long currentUserId = userService.getCurrentUserId();
        if (!ticketEntity.getUser().getId().equals(currentUserId)) {
            throw new TicketNotFoundException("Ticket not found with id: " + id);
        }

        TicketEntity updatedEntity = repo.save(ticketEntity);
        return TicketMapper.toDto(updatedEntity);
    }

    public List<TicketDTO> getUserTickets(Long user_id) {
        List<TicketEntity> tickets = repo.findByUserId(user_id);

        return tickets.stream().map(TicketMapper::toDto).toList();
    }

    public List<TicketDTO> getUserTicketsForCurrentUser() {
        Long user_id = userService.getCurrentUserId();
        return repo.findByUserId(user_id)
                .stream()
                .map(TicketMapper::toDto)
                .toList();
    }
}
