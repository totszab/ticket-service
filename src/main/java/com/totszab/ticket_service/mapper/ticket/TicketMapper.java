package com.totszab.ticket_service.mapper.ticket;

import com.totszab.ticket_service.dto.ticket.TicketCreateRequest;
import com.totszab.ticket_service.dto.ticket.TicketResponse;
import com.totszab.ticket_service.dto.ticket.TicketUpdateRequest;
import com.totszab.ticket_service.entity.ticket.Ticket;

public final class TicketMapper {

    private TicketMapper() {
    }

    public static Ticket toEntity(TicketCreateRequest request) {
        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        return ticket;
    }

    public static Ticket toEntity(TicketUpdateRequest request) {
        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());
        ticket.setPriority(request.getPriority());
        return ticket;
    }

    public static TicketResponse toResponse(Ticket ticket) {
        TicketResponse.TicketResponseBuilder builder = TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .priority(ticket.getPriority())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt());

        if (ticket.getCreatedBy() != null) {
            builder.createdById(ticket.getCreatedBy().getId())
                    .createdByName(ticket.getCreatedBy().getName());
        }

        if (ticket.getAssignedTo() != null) {
            builder.assignedToId(ticket.getAssignedTo().getId())
                    .assignedToName(ticket.getAssignedTo().getName());
        }

        return builder.build();
    }
}