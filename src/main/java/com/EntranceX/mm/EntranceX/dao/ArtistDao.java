package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistDao extends JpaRepository<Artist, Integer> {
    List<Artist> findByArtistNameContainingIgnoreCase(String searchName);
    Artist findByArtistName(String artistName);
//    List<Artist> findByArtistNameContainingIgnoreCase(String searchName);

}
