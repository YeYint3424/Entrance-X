package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dao.TicketOrder_HistoryDao;
import com.EntranceX.mm.EntranceX.dto.EventArtistDto;
import com.EntranceX.mm.EntranceX.models.Artist;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class EventServiceImpli implements EventService {
    @Autowired
    EventDao eventDao;

    @Autowired
    OrganizerDao organizerDao;

    @Autowired
    TicketOrder_HistoryDao ticketOrderDao;

    public EventServiceImpli(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    @Override
    public Event createEvent(EventArtistDto eventArtistDto, int organizerId) throws IOException {
        Organizer organizer=organizerDao.findById(organizerId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Event event = new Event();

        event.setEventName(eventArtistDto.getEventName());
        event.setVenue(eventArtistDto.getVenue());
        event.setDate(eventArtistDto.getDate());
        event.setStartTime(eventArtistDto.getStartTime());
        event.setEndTime(eventArtistDto.getEndTime());
        event.setEventDescription(eventArtistDto.getEventDescription());
        event.setPromotion(eventArtistDto.getPromotion());
        event.setStandardTicketPrice(eventArtistDto.getStandardTicketPrice());
        event.setStandardTicketQuantity(eventArtistDto.getStandardTicketQuantity());
        event.setVipTicketPrice(eventArtistDto.getVipTicketPrice());
        event.setVipTicketQuantity(eventArtistDto.getVipTicketQuantity());
        event.setVvipTicketPrice(eventArtistDto.getVvipTicketPrice());
        event.setVvipTicketQuantity(eventArtistDto.getVvipTicketQuantity());
        event.setStatus(eventArtistDto.getStatus());
        event.setStandardTicketAvailableQuantity(eventArtistDto.getStandardTicketQuantity());
        event.setVipTicketAvailableQuantity(eventArtistDto.getVipTicketQuantity());
        event.setVvipTicketAvailableQuantity(eventArtistDto.getVvipTicketQuantity());
        event.setOrganizer(organizer);

        event.setRequestTime(eventArtistDto.getRequestTime());
        // Encode and set the photo
        byte[] photoBytes = eventArtistDto.getPhoto().getBytes();
        String encodedPhoto = Base64.getEncoder().encodeToString(photoBytes);
        event.setEncodedPhoto(encodedPhoto);

        // Encode and set the kpay QR code
        byte[] kpayQrBytes = eventArtistDto.getKpayQr().getBytes();
        String kpayQrEncoded = Base64.getEncoder().encodeToString(kpayQrBytes);
        event.setKpayQrEncoded(kpayQrEncoded);

        // Encode and set the wavepay QR code
        byte[] wavepayQrBytes = eventArtistDto.getWavepayQr().getBytes();
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
        return eventDao.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found")); }

    @Override
    public List<Event> getEvents() {
        return eventDao.findAll();
    }

    @Override
    public List<Event> getEventForSearch(String eventName) {
        return eventDao.findByEventNameContainingIgnoreCase(eventName);
    }

    @Override
    public List<Event> getUnApproveEvent(int status) {
        return eventDao.findByStatus(status);
    }

    @Override
    public Event approve(int eventId, int status) {
        Event event=eventDao.findById(eventId).orElse(null);
        event.setStatus(status);
        return eventDao.save(event);
    }

    @Override
    public List<Event> getPromotionEvents(int promotion) {
        return eventDao.findByPromotionGreaterThan(promotion);
    }

    @Override
    public List<Artist> getArtistByEventId(int eventId) {
        return eventDao.findArtistsByEventId(eventId);
    }


}
