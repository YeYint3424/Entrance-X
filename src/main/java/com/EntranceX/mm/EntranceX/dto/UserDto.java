package com.EntranceX.mm.EntranceX.dto;

import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import com.EntranceX.mm.EntranceX.models.WatchLater;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private String userName,email,gender,phone,password;

    private List<TicketOrder_History> orderHistory= new ArrayList<>();
    private List<WatchLater> watchLater = new ArrayList<>();
}
