package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {
    Admin findByUserNameAndPassword(String userName, String password);
}
