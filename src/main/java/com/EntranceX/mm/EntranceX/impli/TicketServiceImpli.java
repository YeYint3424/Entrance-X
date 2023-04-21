package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.TicketDao;
import com.EntranceX.mm.EntranceX.dao.TicketOrder_HistoryDao;
import com.EntranceX.mm.EntranceX.dto.TicketDto;
import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import com.EntranceX.mm.EntranceX.models.TicketQr;
import com.EntranceX.mm.EntranceX.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TicketServiceImpli implements TicketService {
    @Autowired
    TicketDao ticketDao;

    @Autowired
    TicketOrder_HistoryDao ticketOrderDao;

    @Override
    public TicketQr saveTickets(TicketDto ticketDto, int voucherId) {
        TicketOrder_History ticketOrder=ticketOrderDao.findById(voucherId).orElse(null);
        TicketQr ticketQr=new TicketQr();
        ticketQr.setEncodedSD1(ticketDto.getEncodedSD1());
        ticketQr.setEncodedSD2(ticketDto.getEncodedSD2());
        ticketQr.setEncodedSD3(ticketDto.getEncodedSD3());
        ticketQr.setEncodedSD4(ticketDto.getEncodedSD4());
        ticketQr.setEncodedSD5(ticketDto.getEncodedSD5());

        ticketQr.setEncodedVIP1(ticketDto.getEncodedVIP1());
        ticketQr.setEncodedVIP2(ticketDto.getEncodedVIP2());
        ticketQr.setEncodedVIP3(ticketDto.getEncodedVIP3());
        ticketQr.setEncodedVIP4(ticketDto.getEncodedVIP4());
        ticketQr.setEncodedVIP5(ticketDto.getEncodedVIP5());

        ticketQr.setEncodedVVIP1(ticketDto.getEncodedVVIP1());
        ticketQr.setEncodedVVIP2(ticketDto.getEncodedVVIP2());
        ticketQr.setEncodedVVIP3(ticketDto.getEncodedVVIP3());
        ticketQr.setEncodedVVIP4(ticketDto.getEncodedVVIP4());
        ticketQr.setEncodedVVIP5(ticketDto.getEncodedVVIP5());

        ticketQr.setTicketOrder(ticketOrder);
        return ticketDao.save(ticketQr);
    }
}
