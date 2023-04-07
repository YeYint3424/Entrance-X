package com.EntranceX.mm.EntranceX.dto;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.User;
import lombok.Data;

@Data
public class TicketOrder_HistoryDto {
    private int order_id;

    private int standardTicketSold, VipTicketQuantitySold,  VVipTicketQuantitySold, totalPrice, status;
    private User user;
    private Event event;

}
