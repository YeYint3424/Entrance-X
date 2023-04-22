package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {
    List <Event> findByOrganizerId(int organizerId);
    List <Event> findByEventNameContainingIgnoreCase(String eventName);
    List <Event> findByStatus(int status);

//    @Query("SELECT e FROM Event e WHERE e.eventName LIKE %:eventName% AND e.promotion > 0")
//    List<Event> findByEventNameContainingAndPromotion(String eventName);


}
