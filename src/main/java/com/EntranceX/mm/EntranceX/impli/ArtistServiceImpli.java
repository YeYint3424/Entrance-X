package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.ArtistDao;
import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.Event_ArtistDao;
import com.EntranceX.mm.EntranceX.dto.ArtistDto;
import com.EntranceX.mm.EntranceX.dto.EventArtistDto;
import com.EntranceX.mm.EntranceX.models.Artist;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Event_Artist;
import com.EntranceX.mm.EntranceX.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpli implements ArtistService {
    @Autowired
    EventDao eventDao;
    @Autowired
    ArtistDao artistDao;
    @Autowired
    Event_ArtistDao event_artistDao;

    @Override
    public Artist addArtistForEvent(EventArtistDto eventArtistDto, int eventId) {
        for (String artistName : eventArtistDto.getArtist()) {
            Artist artist = new Artist();
            artist.setArtist(artistName);
            Artist artist1=artistDao.save(artist);

            Event event=eventDao.findById(eventId).orElse(null);
            Event_Artist event_artist=new Event_Artist();
            event_artist.setArtist(artist1);
            event_artist.setEvent(event);
            event_artistDao.save(event_artist);
        }
        return null;
    }

    @Override
    public List<Artist> getExistingArtists() {
        return artistDao.findAll();
    }
}
