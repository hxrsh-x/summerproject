package com.kapil.NgoEventManager.request;

import lombok.Data;

import java.time.LocalDate;

@Data

public class IssueRequest {
    private String title;
    private String desciption;
    private String status;
    private Long projectId;
    private String priority;
    private LocalDate dueDate;
}
