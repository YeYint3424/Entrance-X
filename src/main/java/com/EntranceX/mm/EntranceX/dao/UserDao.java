package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
