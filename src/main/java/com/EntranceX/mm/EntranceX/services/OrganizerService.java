package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.OrganizerDto;
import com.EntranceX.mm.EntranceX.models.Organizer;

import java.util.List;

public interface OrganizerService {
    Organizer createOrganizer(OrganizerDto organizerDto);
    Organizer getOrganizerById(int organizerId);
    List<Organizer> getAllOrganizerList();
    Organizer editProfile(OrganizerDto organizerDto, int organizerId);
}
