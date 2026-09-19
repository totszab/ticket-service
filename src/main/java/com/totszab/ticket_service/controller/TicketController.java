package com.totszab.ticket_service.controller;

import com.totszab.ticket_service.dto.TicketCreateRequest;
import com.totszab.ticket_service.dto.TicketResponse;
import com.totszab.ticket_service.dto.TicketUpdateRequest;
import com.totszab.ticket_service.entity.Ticket;
import com.totszab.ticket_service.entity.TicketPriority;
import com.totszab.ticket_service.entity.TicketStatus;
import com.totszab.ticket_service.mapper.TicketMapper;
import com.totszab.ticket_service.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> create(@Valid @RequestBody TicketCreateRequest request) {
        Ticket created = ticketService.create(TicketMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(TicketMapper.toResponse(created));
    }

    @GetMapping
    public List<TicketResponse> findAll(
            @RequestParam(required = false) TicketStatus status,
            @RequestParam(required = false) TicketPriority priority) {

        List<Ticket> tickets;
        if (status != null && priority != null) {
            tickets = ticketService.findByStatusAndPriority(status, priority);
        } else if (status != null) {
            tickets = ticketService.findByStatus(status);
        } else if (priority != null) {
            tickets = ticketService.findByPriority(priority);
        } else {
            tickets = ticketService.findAll();
        }

        return tickets.stream()
                .map(TicketMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<TicketResponse> search(@RequestParam String title) {
        return ticketService.searchByTitle(title).stream()
                .map(TicketMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TicketResponse findById(@PathVariable Long id) {
        return TicketMapper.toResponse(ticketService.findById(id));
    }

    @PutMapping("/{id}")
    public TicketResponse update(@PathVariable Long id, @Valid @RequestBody TicketUpdateRequest request) {
        Ticket updated = ticketService.update(id, TicketMapper.toEntity(request));
        return TicketMapper.toResponse(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}