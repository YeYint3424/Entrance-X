package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.OrganizerDto;
import com.EntranceX.mm.EntranceX.models.Organizer;

public interface OrganizerService {
    Organizer createOrganizer(OrganizerDto organizerDto);
    Organizer getOrganizerById(int organizer_id);
}
