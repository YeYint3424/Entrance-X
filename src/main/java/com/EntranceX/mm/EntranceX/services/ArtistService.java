package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.EventArtistDto;
import com.EntranceX.mm.EntranceX.models.Artist;

import java.util.List;

public interface ArtistService {
    Artist addArtistForEvent(EventArtistDto eventArtistDto, int eventId);
    List<Artist> getExistingArtists();
}
