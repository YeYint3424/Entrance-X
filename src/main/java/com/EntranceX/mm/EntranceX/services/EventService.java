package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.EventDto;
import com.EntranceX.mm.EntranceX.models.Event;
import java.io.IOException;
import java.util.List;



public interface EventService {
    Event createEvent(EventDto eventDto, int organizerId) throws IOException;
    List<Event> getEventsByOrganizerId(int organizerId);
    Event showEventDetail(int eventId);
}
