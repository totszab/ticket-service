package com.totszab.ticket_service.dto.ticket;

import com.totszab.ticket_service.entity.ticket.TicketPriority;
import com.totszab.ticket_service.entity.ticket.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketUpdateRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must be at most 150 characters")
    private String title;

    @Size(max = 2000, message = "Description must be at most 2000 characters")
    private String description;

    @NotNull(message = "Status is required")
    private TicketStatus status;

    @NotNull(message = "Priority is required")
    private TicketPriority priority;
}