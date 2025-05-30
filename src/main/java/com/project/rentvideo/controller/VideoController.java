package com.project.rentvideo.controller;

import com.project.rentvideo.dto.VideoRequest;
import com.project.rentvideo.dto.VideoResponse;
import com.project.rentvideo.service.VideoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/videos")
    public ResponseEntity<List<VideoResponse>> getAllAvailableVideos() {
        return ResponseEntity.ok(videoService.getAvailableVideos());
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<VideoResponse> getVideoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(videoService.getVideoById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/videos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VideoResponse> createVideo(@RequestBody VideoRequest request) {
        VideoResponse videoResponse = videoService.createVideo(request);
        return new ResponseEntity<>(videoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/admin/videos/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VideoResponse> updateVideo(@PathVariable Long id, @RequestBody VideoRequest request) {
        try {
            VideoResponse videoResponse = videoService.updateVideo(id, request);
            return ResponseEntity.ok(videoResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/videos/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        try {
            videoService.deleteVideo(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/admin/videos/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<VideoResponse>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }
}
