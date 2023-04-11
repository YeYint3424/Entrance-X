package com.EntranceX.mm.EntranceX.services;


import com.EntranceX.mm.EntranceX.dto.UserDto;
import com.EntranceX.mm.EntranceX.dto.WatchLaterDto;
import com.EntranceX.mm.EntranceX.models.User;
import com.EntranceX.mm.EntranceX.models.WatchLater;

public interface UserService {
        User createUser(UserDto userDto);
        WatchLater saveEventToWatchLater(int userId, int eventId);
    }


