package zela.ticket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import zela.ticket.dto.TicketDTO;
import zela.ticket.service.TicketService;

@RestController
@AllArgsConstructor
public class TicketController {
    private final TicketService service;

    @PostMapping
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return service.createTicket(ticketDTO);
    }

    @GetMapping("/byId/{id}")
    public TicketDTO getTicketById(@PathVariable Long id) {
        return service.getTicketById(id);
    }

    @GetMapping("/byUser/{user_id}")
    public List<TicketDTO> getUserTickets(@PathVariable Long user_id) {
        return service.getUserTickets(user_id);
    }
}
