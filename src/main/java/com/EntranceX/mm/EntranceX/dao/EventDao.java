package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EventDao extends JpaRepository<Event, Integer> {
    List <Event> findByOrganizerId(int organizerId);
}
