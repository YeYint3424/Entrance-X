package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.dto.WatchLaterDto;
import com.EntranceX.mm.EntranceX.models.WatchLater;

public interface WatchLaterService {
    WatchLater saveEventToWatchLater(int userId, int eventId);
    WatchLater removeEventFromWatchLater(int userId, int eventId);
    WatchLater findBySpecificUser(int userId, int eventId);
}
