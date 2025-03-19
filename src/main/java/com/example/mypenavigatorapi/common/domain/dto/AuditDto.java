package com.example.mypenavigatorapi.common.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditDto {
    private Date createdAt;
    private Date updatedAt;
    private boolean active;
}
