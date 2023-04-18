package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.AdminDto;
import com.EntranceX.mm.EntranceX.models.Admin;

public interface AdminService {
    Admin createAdmin(AdminDto adminDto);
}
