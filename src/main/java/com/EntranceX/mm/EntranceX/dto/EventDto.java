package com.EntranceX.mm.EntranceX.dto;

import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import com.EntranceX.mm.EntranceX.models.WatchLater;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Data
public class EventDto {
    private String eventName, venue, startTime, endTime,artist, eventDescription, encodedPhoto, kpayQrEncoded, wavepayQrEncoded;
    private int promotion, standardTicketPrice, standardTicketQuantity, vipTicketPrice, vipTicketQuantity, vvipTicketPrice, vvipTicketQuantity, status;
    private LocalDate date;
    private MultipartFile photo,kpayQr,wavepayQr;
    private Organizer organizer;
    private List<TicketOrder_History> orderHistory= new ArrayList<>();
    private List<WatchLater> watchLater= new ArrayList<>();


    }


