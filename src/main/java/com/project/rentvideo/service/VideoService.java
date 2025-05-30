package com.project.rentvideo.service;

import com.project.rentvideo.dto.VideoRequest;
import com.project.rentvideo.dto.VideoResponse;
import com.project.rentvideo.model.Video;
import com.project.rentvideo.repository.VideoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public List<VideoResponse> getAllVideos() {
        return videoRepository.findAll()
                .stream()
                .map(this::mapToVideoResponse)
                .collect(Collectors.toList());
    }

    public List<VideoResponse> getAvailableVideos() {
        return videoRepository.findByAvailabilityStatusTrue()
                .stream()
                .map(this::mapToVideoResponse)
                .collect(Collectors.toList());
    }

    public VideoResponse getVideoById(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Video not found with id: " + id));
        return mapToVideoResponse(video);
    }

    public VideoResponse createVideo(VideoRequest request) {
        Video video = Video.builder()
                .title(request.getTitle())
                .director(request.getDirector())
                .genre(request.getGenre())
                .availabilityStatus(request.isAvailabilityStatus())
                .build();
        
        Video savedVideo = videoRepository.save(video);
        return mapToVideoResponse(savedVideo);
    }

    public VideoResponse updateVideo(Long id, VideoRequest request) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Video not found with id: " + id));
        
        video.setTitle(request.getTitle());
        video.setDirector(request.getDirector());
        video.setGenre(request.getGenre());
        video.setAvailabilityStatus(request.isAvailabilityStatus());
        
        Video updatedVideo = videoRepository.save(video);
        return mapToVideoResponse(updatedVideo);
    }

    public void deleteVideo(Long id) {
        if (!videoRepository.existsById(id)) {
            throw new EntityNotFoundException("Video not found with id: " + id);
        }
        
        videoRepository.deleteById(id);
    }
    
    private VideoResponse mapToVideoResponse(Video video) {
        return VideoResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .director(video.getDirector())
                .genre(video.getGenre())
                .availabilityStatus(video.isAvailabilityStatus())
                .build();
    }
}
