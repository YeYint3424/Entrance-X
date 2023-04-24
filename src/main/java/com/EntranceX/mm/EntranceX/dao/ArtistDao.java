package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistDao extends JpaRepository<Artist, Integer> {
}
