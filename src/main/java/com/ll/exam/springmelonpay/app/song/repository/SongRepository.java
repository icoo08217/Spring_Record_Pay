package com.ll.exam.springmelonpay.app.song.repository;

import com.ll.exam.springmelonpay.app.song.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song , Long> {
}
