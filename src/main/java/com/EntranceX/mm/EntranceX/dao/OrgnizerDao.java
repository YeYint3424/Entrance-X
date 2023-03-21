package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgnizerDao extends JpaRepository <Organizer, Integer>{
}
