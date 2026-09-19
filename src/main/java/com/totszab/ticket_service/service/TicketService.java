package com.totszab.ticket_service.service;

import com.totszab.ticket_service.entity.ticket.Ticket;
import com.totszab.ticket_service.entity.ticket.TicketPriority;
import com.totszab.ticket_service.entity.ticket.TicketStatus;
import com.totszab.ticket_service.exception.TicketNotFoundException;
import com.totszab.ticket_service.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket update(Long id, Ticket updatedTicket) {
        Ticket existing = findById(id);
        existing.setTitle(updatedTicket.getTitle());
        existing.setDescription(updatedTicket.getDescription());
        existing.setStatus(updatedTicket.getStatus());
        existing.setPriority(updatedTicket.getPriority());
        return ticketRepository.save(existing);
    }

    public void delete(Long id) {
        Ticket existing = findById(id);
        ticketRepository.delete(existing);
    }

    public List<Ticket> findByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status);
    }

    public List<Ticket> findByPriority(TicketPriority priority) {
        return ticketRepository.findByPriority(priority);
    }

    public List<Ticket> findByStatusAndPriority(TicketStatus status, TicketPriority priority) {
        return ticketRepository.findByStatusAndPriority(status, priority);
    }

    public List<Ticket> searchByTitle(String title) {
        return ticketRepository.findByTitleContainingIgnoreCase(title);
    }
}