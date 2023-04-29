package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Artist;
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
    List<Event> findByPromotionGreaterThan(int promotion);

    @Query("SELECT ea.artist FROM Event_Artist ea WHERE ea.event.id = :eventId")
    List<Artist> findArtistsByEventId(int eventId);
    List<Event> findByEventNameContainingIgnoreCaseAndOrganizerId(String eventName, int organizerId);

//    @Query("SELECT e FROM Event e WHERE e.eventName LIKE %:eventName% AND e.promotion > 0")
//    List<Event> findByEventNameContainingAndPromotion(String eventName);


}
