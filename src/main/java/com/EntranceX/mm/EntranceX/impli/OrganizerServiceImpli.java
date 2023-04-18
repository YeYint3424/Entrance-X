package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.OrganizerDto;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        organizer.setUserName(organizerDto.getUserName());
        organizer.setCompanyName(organizerDto.getCompanyName());
        organizer.setOrganizerEmail(organizerDto.getOrganizerEmail());
        organizer.setCompanyEmail(organizerDto.getCompanyEmail());
        organizer.setOrganizerPhone(organizerDto.getOrganizerPhone());
        organizer.setCompanyPhone(organizerDto.getCompanyPhone());
        organizer.setStatus(organizerDto.getStatus());
        organizer.setCompanyAddress(organizerDto.getCompanyAddress());
        organizer.setCompanyBio(organizerDto.getCompanyBio());
        organizer.setStatus(organizerDto.getStatus());

        String encodedPassword = passwordEncoder.encode(organizerDto.getPassword());
        organizer.setPassword(encodedPassword);

        return organizerDao.save(organizer);
    }

    @Override
    public Organizer getOrganizerById(int organizerId) {
        return organizerDao.findById(organizerId).orElse(null);


    }

    @Override
    public List<Organizer> getAllOrganizerList() {
        return organizerDao.findAll();
    }

    @Override
    public Organizer editProfile(OrganizerDto organizerDto, int organizerId) {
        Organizer organizer=organizerDao.findById(organizerId).orElse(null);
        organizer.setUserName(organizerDto.getUserName());
        organizer.setOrganizerEmail(organizerDto.getOrganizerEmail());
        organizer.setOrganizerPhone(organizerDto.getOrganizerPhone());
        organizer.setOrganizerPhone(organizerDto.getOrganizerPhone());
        organizer.setCompanyPhone(organizerDto.getCompanyPhone());
        organizer.setCompanyEmail(organizerDto.getCompanyEmail());
        organizer.setCompanyAddress(organizerDto.getCompanyAddress());
        organizer.setCompanyBio(organizerDto.getCompanyBio());
        String password=passwordEncoder.encode(organizerDto.getPassword());
        organizer.setPassword(password);
        return organizerDao.save(organizer);
    }
}
