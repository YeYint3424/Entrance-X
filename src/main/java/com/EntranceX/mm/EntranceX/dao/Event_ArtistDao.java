package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Event_Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Event_ArtistDao extends JpaRepository<Event_Artist, Integer> {
}
