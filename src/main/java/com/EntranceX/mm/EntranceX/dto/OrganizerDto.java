package com.EntranceX.mm.EntranceX.dto;

import com.EntranceX.mm.EntranceX.models.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrganizerDto {
    private String userName, companyName,organizerEmail,companyEmail,organizerPhone, companyPhone,role,companyAddress,companyBio, password;
    private int status;
    private List<Event> events= new ArrayList<>();
}
