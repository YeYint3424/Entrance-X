package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketOrder_HistoryDao extends JpaRepository<TicketOrder_History, Integer> {
}
