package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.Event_ArtistDao;
import com.EntranceX.mm.EntranceX.models.Event_Artist;
import com.EntranceX.mm.EntranceX.services.Event_ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Event_ArtistServiceImpli implements Event_ArtistService {
    @Autowired
    Event_ArtistDao event_artistDao;


}
