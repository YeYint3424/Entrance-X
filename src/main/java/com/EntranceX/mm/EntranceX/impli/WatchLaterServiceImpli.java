package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dao.WatchLaterDao;
import com.EntranceX.mm.EntranceX.dto.WatchLaterDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.User;
import com.EntranceX.mm.EntranceX.models.WatchLater;
import com.EntranceX.mm.EntranceX.services.WatchLaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchLaterServiceImpli implements WatchLaterService {
    @Autowired
    private WatchLaterDao watchLaterDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EventDao eventDao;

    @Override
    public WatchLater saveEventToWatchLater(int userId, int eventId) {
        User user = userDao.findById(userId).orElse(null); // Retrieve user by ID from the database
        Event event = eventDao.findById(eventId).orElse(null); // Retrieve event by ID from the database

        if (user == null || event == null) {
            throw new IllegalArgumentException("Invalid user or event ID");
        }

        WatchLater watchLater = new WatchLater();
        watchLater.setUser(user);
        watchLater.setEvent(event);

        return watchLaterDao.save(watchLater);
    }


    @Override
    public WatchLater removeEventFromWatchLater(int userId, int eventId) {
        WatchLater watchLater=watchLaterDao.findByUserIdAndEventId(userId, eventId);
        if (watchLater != null) {
            watchLaterDao.delete(watchLater);
        }

        return watchLater;
    }

    @Override
    public WatchLater findBySpecificUser(int userId, int eventId) {
        return watchLaterDao.findByUserIdAndEventId(userId, eventId);

    }
}
