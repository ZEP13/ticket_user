package zela.ticket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import zela.ticket.dto.TicketDTO;
import zela.ticket.entity.TicketEntity;
import zela.ticket.exception.TicketExceptions.TicketNotFoundException;
import zela.ticket.mapper.TicketMapper;
import zela.ticket.repository.TicketRepo;

@Service
public class TicketService {
    private final TicketRepo repo;

    public TicketService(TicketRepo repo) {
        this.repo = repo;
    }

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        TicketEntity ticketEntity = TicketMapper.toEntity(ticketDTO);
        TicketEntity savedEntity = repo.save(ticketEntity);
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
        TicketEntity updatedEntity = repo.save(ticketEntity);
        return TicketMapper.toDto(updatedEntity);
    }

    public List<TicketDTO> getUserTickets(Long user_id) {
        List<TicketEntity> tickets = repo.findByUserId(user_id);

        return tickets.stream().map(TicketMapper::toDto).toList();
    }
}
