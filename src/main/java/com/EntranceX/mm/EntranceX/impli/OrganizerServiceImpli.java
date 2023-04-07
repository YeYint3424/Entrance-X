package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.OrganizerDto;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServiceImpli implements OrganizerService {
    @Autowired
    OrganizerDao organizerDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public OrganizerServiceImpli(OrganizerDao organizerDao){
        this.organizerDao=organizerDao;
    }
    @Override
    public Organizer createOrganizer(OrganizerDto organizerDto) {
        Organizer organizer=new Organizer();
        organizer.setOrganizerName(organizerDto.getOrganizerName());
        organizer.setOrganizerName(organizerDto.getOrganizerName());
        organizer.setCompanyName(organizerDto.getCompanyName());
        organizer.setOrganizerEmail(organizerDto.getOrganizerEmail());
        organizer.setCompanyEmail(organizerDto.getCompanyEmail());
        organizer.setOrganizerPhone(organizerDto.getOrganizerPhone());
        organizer.setCompanyPhone(organizerDto.getCompanyPhone());
        organizer.setRole(organizerDto.getRole());
        organizer.setCompanyAddress(organizerDto.getCompanyAddress());
        organizer.setCompanyBio(organizerDto.getCompanyBio());
        organizer.setStatus(organizerDto.getStatus());

        String encodedPassword = passwordEncoder.encode(organizerDto.getPassword());
        organizer.setPassword(encodedPassword);

        return organizerDao.save(organizer);
    }
}
