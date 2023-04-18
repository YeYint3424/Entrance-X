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
import java.util.Optional;

@Service
public class UserServiceImpli implements UserService {
    @Autowired
    private UserDao userDao;

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
    public List<User> getAllUserList() {
        return userDao.findAll();
    }

    @Override
    public User getUserData(int userId) {
        return userDao.findById(userId).orElse(null);
    }

    @Override
    public User editProfile(UserDto userDto, int userId) {
        User user=userDao.findById(userId).orElse(null);
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setGender(userDto.getGender());
        String encodedPassword=passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        return userDao.save(user);
    }


}
