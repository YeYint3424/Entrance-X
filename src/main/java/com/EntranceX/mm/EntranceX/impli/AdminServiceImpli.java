package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.AdminDao;
import com.EntranceX.mm.EntranceX.dto.AdminDto;
import com.EntranceX.mm.EntranceX.models.Admin;
import com.EntranceX.mm.EntranceX.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpli implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Admin createAdmin(AdminDto adminDto) {
        Admin admin=new Admin();
        admin.setUserName(adminDto.getUserName());

        String encodedPassword = passwordEncoder.encode(adminDto.getPassword());
        admin.setPassword(encodedPassword);
        return adminDao.save(admin);
    }
}
