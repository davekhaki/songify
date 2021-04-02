package com.songify.api.repository;

import com.songify.api.model.Playlist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findAllByPlays(int plays, Pageable pageable);

    Playlist findById(long id);
}