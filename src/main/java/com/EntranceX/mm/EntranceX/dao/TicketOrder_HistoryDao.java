package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TicketOrder_HistoryDao extends JpaRepository<TicketOrder_History, Integer> {
    List<TicketOrder_History> findByUserId(int userId);
    List<TicketOrder_History> findByStatus(int status);
    TicketOrder_History findByIdAndUserIdAndEventId(int orderId, int userId, int eventId);


}
