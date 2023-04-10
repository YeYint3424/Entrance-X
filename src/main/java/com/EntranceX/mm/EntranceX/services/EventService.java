package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.EventDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Organizer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface EventService {
    Event createEvent(EventDto eventDto, int organizer_id) throws IOException;
    List<Event> getEventsByOrganizerId(int organizer_id);
}
