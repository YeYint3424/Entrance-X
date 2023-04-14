package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.TicketOrder_HistoryDto;
import com.EntranceX.mm.EntranceX.models.TicketOrder_History;

import java.io.IOException;

public interface OrderService{
    TicketOrder_History orderRequest(TicketOrder_HistoryDto ticketOrderDto) throws IOException;
}
