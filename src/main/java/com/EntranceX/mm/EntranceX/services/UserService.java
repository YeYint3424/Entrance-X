package com.EntranceX.mm.EntranceX.services;


import com.EntranceX.mm.EntranceX.dto.UserDto;
import com.EntranceX.mm.EntranceX.models.User;

public interface UserService {
        User createUser(UserDto userDto);
    }


