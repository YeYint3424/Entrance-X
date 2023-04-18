package com.EntranceX.mm.EntranceX.services;


import com.EntranceX.mm.EntranceX.dto.UserDto;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.User;
import com.EntranceX.mm.EntranceX.models.WatchLater;

import java.util.List;

public interface UserService {
        User createUser(UserDto userDto);
        List<User> getAllUserList();
        User getUserData(int userId);
        User editProfile(UserDto userDto, int userId);

    }


