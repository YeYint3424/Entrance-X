package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dao.TicketOrder_HistoryDao;
import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dto.TicketOrder_HistoryDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import com.EntranceX.mm.EntranceX.models.User;
import com.EntranceX.mm.EntranceX.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class OrderServiceImpli implements OrderService {
    @Autowired
    UserDao userDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    TicketOrder_HistoryDao ticketOrderDao;

    @Override
    public TicketOrder_History orderRequest(TicketOrder_HistoryDto ticketOrderDto)throws IOException {
        TicketOrder_History ticketOrder=new TicketOrder_History();
        ticketOrder.setStandardTicketSold(ticketOrderDto.getStandardTicketSold());
        ticketOrder.setVipTicketSold(ticketOrderDto.getVipTicketSold());
        ticketOrder.setVvipTicketSold(ticketOrderDto.getVvipTicketSold());
        ticketOrder.setStatus(ticketOrderDto.getStatus());
        User user=userDao.findById(ticketOrderDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        Event event=eventDao.findById(ticketOrderDto.getEventId()).orElseThrow(() -> new EventNotFoundException("Event not found"));
        ticketOrder.setUser(user);
        ticketOrder.setEvent(event);
        ticketOrder.setTotalPrice(ticketOrderDto.getTotalPrice());
        byte[] paymentScreenShotPhoto=ticketOrderDto.getPaymentScreenShot().getBytes();
        String encodedPaymentScreenShot = Base64.getEncoder().encodeToString(paymentScreenShotPhoto);
        ticketOrder.setEncodedPaymentScreenShot(encodedPaymentScreenShot);

        return ticketOrderDao.save(ticketOrder);
    }

    @Override
    public List<TicketOrder_History> getAllOrder() {
        return ticketOrderDao.findAll();
    }

    @Override
    public TicketOrder_History saveTicketQr(String ticketQR) {
        TicketOrder_History ticketOrder=new TicketOrder_History();
        ticketOrder.setEncodedTicketQR(ticketQR);
        return ticketOrderDao.save(ticketOrder);
    }

    @Override
    public List<TicketOrder_History> getUserOrderList(int userId) {
        return ticketOrderDao.findByUserId(userId);
    }
}
