package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dto.EventDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class EventServiceImpli implements EventService {
    @Autowired
    EventDao eventDao;

    public EventServiceImpli(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    @Override
    public Event createEvent(EventDto eventDto) throws IOException {
        Event event = new Event();
        event.setEventName(eventDto.getEventName());
        event.setVenue(eventDto.getVenue());
        event.setDate(eventDto.getDate());
        event.setStartTime(eventDto.getStartTime());
        event.setEndTime(eventDto.getEndTime());
        event.setArtist(eventDto.getArtist());
        event.setEventDescription(eventDto.getEventDescription());
        event.setPromotion(eventDto.getPromotion());
        event.setStandardTicketPrice(eventDto.getStandardTicketPrice());
        event.setStandardTicketQuantity(eventDto.getStandardTicketQuantity());
        event.setVipTicketPrice(eventDto.getVipTicketPrice());
        event.setVipTicketQuantity(eventDto.getVipTicketQuantity());
        event.setVvipTicketPrice(eventDto.getVvipTicketPrice());
        event.setVvipTicketQuantity(eventDto.getVvipTicketQuantity());
        event.setStatus(eventDto.getStatus());

        // Encode and set the photo
        byte[] photoBytes = eventDto.getPhoto().getBytes();
        String encodedPhoto = Base64.getEncoder().encodeToString(photoBytes);
        event.setEncodedPhoto(encodedPhoto);

        // Encode and set the kpay QR code
        byte[] kpayQrBytes = eventDto.getKpayQr().getBytes();
        String kpayQrEncoded = Base64.getEncoder().encodeToString(kpayQrBytes);
        event.setKpayQrEncoded(kpayQrEncoded);

        // Encode and set the wavepay QR code
        byte[] wavepayQrBytes = eventDto.getWavepayQr().getBytes();
        String wavepayQrEncoded = Base64.getEncoder().encodeToString(wavepayQrBytes);
        event.setWavepayQrEncoded(wavepayQrEncoded);

        return eventDao.save(event);
    }
}
