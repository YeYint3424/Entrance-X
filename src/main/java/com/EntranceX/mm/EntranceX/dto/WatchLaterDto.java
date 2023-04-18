package com.EntranceX.mm.EntranceX.dto;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.User;
import lombok.Data;

@Data
public class WatchLaterDto {
    private int userId, eventId;
    private Event event;
    private User user;
}
