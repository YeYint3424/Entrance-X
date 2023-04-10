package com.EntranceX.mm.EntranceX.dto;

import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import com.EntranceX.mm.EntranceX.models.WatchLater;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private String name, userName,email,gender,phone, role,password;
    private LocalDate dateOfBirth;
    private List<TicketOrder_History> orderHistory= new ArrayList<>();
    private List<WatchLater> watchLater = new ArrayList<>();
}
