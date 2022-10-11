package com.ll.exam.app.song.repository;

import com.ll.exam.app.song.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song , Long> {
}
