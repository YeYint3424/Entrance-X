package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.EventDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpli implements EventService {
    @Autowired
    EventDao eventDao;

    @Autowired
    OrganizerDao organizerDao;

    public EventServiceImpli(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    @Override
    public Event createEvent(EventDto eventDto, int organizerId) throws IOException {
        Organizer organizer=organizerDao.findById(organizerId).orElseThrow(() -> new UserNotFoundException("User not found"));
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
        event.setOrganizer(organizer);
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

    @Override
    public List<Event> getEventsByOrganizerId(int organizerId) {
        return eventDao.findByOrganizerId(organizerId);
    }

    @Override
    public Event showEventDetail(int eventId) {
        return eventDao.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found"));


    }


}
