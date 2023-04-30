package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.TicketOrder_HistoryDto;
import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

public interface OrderService{
    TicketOrder_History orderRequest(TicketOrder_HistoryDto ticketOrderDto) throws IOException;
    List<TicketOrder_History> getAllOrder();
    TicketOrder_History saveTicketQr(String ticketQR);
    List<TicketOrder_History> getUserOrderList(int userId);
    TicketOrder_History getOrderWithId(int voucherId);
    TicketOrder_History approve(int voucherId, int status) throws Exception;
    TicketOrder_History cancel(int voucherId, int status);
    List<TicketOrder_History> getUnApproveOrder(int status);
    TicketOrder_History decreaseAvailableTicket(int standardTicketSold, int vipTicketSold,
                                  int vvipTicketSold, int voucherId);
    TicketOrder_History getSpecificTicketForUser(int orderId, int userId, int eventId);
}
