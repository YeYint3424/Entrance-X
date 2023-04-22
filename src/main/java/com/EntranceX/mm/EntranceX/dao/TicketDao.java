package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.TicketQr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TicketDao extends JpaRepository<TicketQr, Integer> {
}
