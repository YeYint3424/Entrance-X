package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
