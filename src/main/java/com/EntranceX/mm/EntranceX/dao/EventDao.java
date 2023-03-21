package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {
}
