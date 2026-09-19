package com.totszab.ticket_service.repository;

import com.totszab.ticket_service.entity.Ticket;
import com.totszab.ticket_service.entity.TicketPriority;
import com.totszab.ticket_service.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByPriority(TicketPriority priority);

    List<Ticket> findByStatusAndPriority(TicketStatus status, TicketPriority priority);

    List<Ticket> findByTitleContainingIgnoreCase(String title);
}
