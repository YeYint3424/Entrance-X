package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.EventDto;
import com.EntranceX.mm.EntranceX.models.Event;

import java.io.IOException;


public interface EventService {
    Event createEvent(EventDto eventDto) throws IOException;
}
