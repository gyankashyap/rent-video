package com.project.rentvideo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponse {
    private Long id;
    private String title;
    private String director;
    private String genre;
    private boolean availabilityStatus;
}
