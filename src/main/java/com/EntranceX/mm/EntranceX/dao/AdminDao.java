package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {
    Admin findByUserName(String userName);



}
