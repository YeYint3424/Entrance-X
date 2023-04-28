package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.TicketQr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketQrDao extends JpaRepository<TicketQr, Integer> {
    List<TicketQr> findByTicketOrderId(int eventId);
}
