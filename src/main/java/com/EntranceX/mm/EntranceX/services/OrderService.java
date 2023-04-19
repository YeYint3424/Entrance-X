package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.TicketOrder_HistoryDto;
import com.EntranceX.mm.EntranceX.models.TicketOrder_History;

import java.io.IOException;
import java.util.List;

public interface OrderService{
    TicketOrder_History orderRequest(TicketOrder_HistoryDto ticketOrderDto) throws IOException;
    List<TicketOrder_History> getAllOrder();
    TicketOrder_History saveTicketQr(String ticketQR);
    List<TicketOrder_History> getUserOrderList(int userId);
}
