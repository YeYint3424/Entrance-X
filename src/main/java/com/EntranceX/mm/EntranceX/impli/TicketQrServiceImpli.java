package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.TicketQrDao;
import com.EntranceX.mm.EntranceX.models.TicketQr;
import com.EntranceX.mm.EntranceX.services.TicketQrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketQrServiceImpli implements TicketQrService {
    @Autowired
    TicketQrDao ticketQrDao;

    @Override
    public List<TicketQr> findByOrderId(int orderId) {

        return ticketQrDao.findByTicketOrderId(orderId);

    }
}
