package com.example.mypenavigatorapi.enrollments.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SaveUserModuleTrackingDto {
    private Boolean isCompleted;
    private Boolean isStarted;
    private Boolean isBLocked;
    private Boolean isVideoCompleted;
    private Boolean isMaterialDownloaded;
    private Integer testScore;
    private Integer progress;
    private Date startedAt;
    private Date completedAt;
}
