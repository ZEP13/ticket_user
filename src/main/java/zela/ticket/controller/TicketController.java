package zela.ticket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import zela.ticket.dto.TicketDTO;
import zela.ticket.service.TicketService;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService service;

    @PostMapping("/create")
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return service.createTicket(ticketDTO);
    }

    @GetMapping("/byId/{id}")
    public TicketDTO getTicketById(@PathVariable Long id) {
        return service.getTicketById(id);
    }

    @PostMapping("/end/{id}")
    public TicketDTO endTicket(@PathVariable Long id) {
        return service.endTicket(id);
    }

    @GetMapping("/byUser")
    public List<TicketDTO> getUserTickets() {
        return service.getUserTicketsForCurrentUser();
    }

    @GetMapping("/all")
    public List<TicketDTO> getAllTicket() {
        return service.getAllTickets();
    }

}
