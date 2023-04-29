package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.EventArtistDto;
import com.EntranceX.mm.EntranceX.models.Artist;
import com.EntranceX.mm.EntranceX.models.Event;
import java.io.IOException;
import java.util.List;



public interface EventService {
    Event createEvent(EventArtistDto eventArtistDto, int organizerId) throws IOException;
    List<Event> getEventsByOrganizerId(int organizerId);
    Event showEventDetail(int eventId);
    List<Event> getEvents();
    List<Event> getEventForSearch(String eventName);
    List<Event> getUnApproveEvent(int status);
    Event approve(int eventId, int status);
    Event cancel(int eventId, int status);
    List<Event> getPromotionEvents(int promotion);
    List<Artist> getArtistByEventId(int eventId);
    List<Event> getEventForSearchOrganizer(String eventName, int organizerId);

}
