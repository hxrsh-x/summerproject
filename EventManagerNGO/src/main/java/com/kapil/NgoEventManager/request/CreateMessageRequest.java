package com.kapil.NgoEventManager.request;

import lombok.Data;

@Data
public class CreateMessageRequest {

    private Long senderId;
    private String Content;
    private Long projectId;
}
