package com.EntranceX.mm.EntranceX.impli;


import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dao.WatchLaterDao;
import com.EntranceX.mm.EntranceX.dto.UserDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.User;
import com.EntranceX.mm.EntranceX.models.WatchLater;
import com.EntranceX.mm.EntranceX.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpli implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private WatchLaterDao watchLaterDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpli(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setPhone(userDto.getPhone());


        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);

        return userDao.save(user);
    }

    @Override
    public WatchLater saveEventToWatchLater(int userId, int eventId) {
        User user = new User();
        user.setId(userId);
        Event event = new Event();
        event.setId(eventId);
        WatchLater watchLater=new WatchLater();
        watchLater.setUser(user);
        watchLater.setEvent(event);
        return watchLaterDao.save(watchLater);
    }

    @Override
    public List<Event> getEvents() {
        return eventDao.findAll();
    }
}
