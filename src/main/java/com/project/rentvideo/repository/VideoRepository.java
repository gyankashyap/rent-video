package com.project.rentvideo.repository;

import com.project.rentvideo.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByAvailabilityStatusTrue();
}
