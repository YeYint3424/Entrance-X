package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.WatchLater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchLaterDao extends JpaRepository<WatchLater,Integer> {
    WatchLater findByUserIdAndEventId(int userId, int eventId);
    List<WatchLater> findByUserId(int userId);
}
