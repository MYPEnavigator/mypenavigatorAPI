package com.example.mypenavigatorapi.communication.domain.dto;

import com.example.mypenavigatorapi.common.domain.dto.AuditDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto extends AuditDto {
    private Long id;
    private String text;
    private String title;
}
