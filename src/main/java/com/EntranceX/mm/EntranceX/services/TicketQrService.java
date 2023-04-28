package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.models.TicketQr;

import java.util.List;

public interface TicketQrService {
    List<TicketQr> findByOrderId(int orderId);
}
