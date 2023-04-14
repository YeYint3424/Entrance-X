package com.EntranceX.mm.EntranceX.dto;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TicketOrder_HistoryDto {
    private int userId, eventId;
    private int standardTicketSold, vipTicketSold,  vvipTicketSold, totalPrice, status;
    private MultipartFile paymentScreenShot;
    private User user;
    private Event event;

}
