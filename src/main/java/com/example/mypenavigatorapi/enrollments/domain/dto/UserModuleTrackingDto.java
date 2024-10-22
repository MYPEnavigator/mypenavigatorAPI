package com.example.mypenavigatorapi.enrollments.domain.dto;

import com.example.mypenavigatorapi.courses.domain.dto.ModuleDto;
import com.example.mypenavigatorapi.courses.domain.entities.Module;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserModuleTrackingDto {
    private Long id;
    private Boolean isCompleted;
    private Boolean isStarted;
    private Boolean isBlocked;
    private Boolean isVideoCompleted;
    private Boolean isMaterialDownloaded;
    private Integer testScore;
    private Integer progress;
    private Date startedAt;
    private Date completedAt;
    private ModuleDto module;
}
