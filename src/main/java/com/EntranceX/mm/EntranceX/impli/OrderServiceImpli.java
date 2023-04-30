package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.config.QRCodeGenerator;
import com.EntranceX.mm.EntranceX.dao.*;
import com.EntranceX.mm.EntranceX.dto.TicketOrder_HistoryDto;
import com.EntranceX.mm.EntranceX.models.*;
import com.EntranceX.mm.EntranceX.services.OrderService;
import com.google.zxing.WriterException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
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
    @Autowired
    QRCodeGenerator qrCodeGenerator;
    @Autowired
    TicketQrDao ticketQrDao;

    @Override
    public TicketOrder_History orderRequest(TicketOrder_HistoryDto ticketOrderDto)throws IOException {
        TicketOrder_History ticketOrder=new TicketOrder_History();
        ticketOrder.setStandardTicketSold(ticketOrderDto.getStandardTicketSold());
        ticketOrder.setVipTicketSold(ticketOrderDto.getVipTicketSold());
        ticketOrder.setVvipTicketSold(ticketOrderDto.getVvipTicketSold());
        ticketOrder.setStatus(ticketOrderDto.getStatus());
        ticketOrder.setRequestTime(ticketOrderDto.getRequestTime());
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
        return ticketOrderDao.save(ticketOrder);
    }

    @Override
    public List<TicketOrder_History> getUserOrderList(int userId) {
        return ticketOrderDao.findByUserId(userId);
    }

    @Override
    public TicketOrder_History getOrderWithId(int voucherId) {
        return ticketOrderDao.findById(voucherId).orElse(null);
    }

    @Override
    public TicketOrder_History approve(int voucherId, int status) throws Exception {
        TicketOrder_History ticketOrder=ticketOrderDao.findById(voucherId).orElse(null);
        assert ticketOrder != null;
        int standardTicketsSold = ticketOrder.getStandardTicketSold();
        int vipTicketsSold = ticketOrder.getVipTicketSold();
        int vvipTicketsSold = ticketOrder.getVvipTicketSold();
        int totalTicketsSold = standardTicketsSold + vipTicketsSold + vvipTicketsSold;

        for (int i = 0; i < totalTicketsSold; i++) {
            String qrCodeData = RandomStringUtils.randomAlphanumeric(10);

            int qrCodeSize = 200;
            byte[] qrCodeImage = QRCodeGenerator.generateQRCode(qrCodeData, qrCodeSize);

            TicketQr ticket = new TicketQr();

            if (i < standardTicketsSold) {
                ticket.setTicketType("Standard");
            } else if (i < standardTicketsSold + vipTicketsSold) {
                ticket.setTicketType("Vip");
            } else {
                ticket.setTicketType("VVip");
            }

            String encodedQr = Base64.getEncoder().encodeToString(qrCodeImage);
            ticket.setTicketQr(encodedQr);
            ticket.setTicketOrder(ticketOrder);
            ticketQrDao.save(ticket);
        }

        ticketOrder.setStatus(status);
        ticketOrder.setPurchaseSuccessTime(LocalDateTime.now());
        return ticketOrderDao.save(ticketOrder);
    }

    @Override
    public TicketOrder_History cancel(int voucherId, int status) {
        TicketOrder_History ticketOrder=ticketOrderDao.findById(voucherId).orElse(null);


        assert ticketOrder != null;
        ticketOrder.setStatus(status);
        return ticketOrderDao.save(ticketOrder);
    }

    @Override
    public List<TicketOrder_History> getUnApproveOrder(int status) {
        return ticketOrderDao.findByStatus(status);
    }
    @Override
    public TicketOrder_History decreaseAvailableTicket(int standardTicketSold, int vipTicketSold, int vvipTicketSold, int voucherId) {
        TicketOrder_History ticketOrder=ticketOrderDao.findById(voucherId).orElse(null);
        assert ticketOrder != null;
        ticketOrder.getEvent().setStandardTicketAvailableQuantity(ticketOrder.getEvent().getStandardTicketAvailableQuantity()-standardTicketSold);
        ticketOrder.getEvent().setVipTicketAvailableQuantity(ticketOrder.getEvent().getVipTicketAvailableQuantity()-vipTicketSold);
        ticketOrder.getEvent().setVvipTicketAvailableQuantity(ticketOrder.getEvent().getVvipTicketAvailableQuantity()-vvipTicketSold);
        return ticketOrderDao.save(ticketOrder);
    }

    @Override
    public TicketOrder_History getSpecificTicketForUser(int orderId, int userId, int eventId) {
        return ticketOrderDao.findByIdAndUserIdAndEventId(orderId, userId, eventId);
    }
}
