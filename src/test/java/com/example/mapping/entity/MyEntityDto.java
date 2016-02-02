package com.example.mapping.entity;

import java.util.List;

public class MyEntityDto {
    private List<FileDataDto> attachments;

    public List<FileDataDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<FileDataDto> attachments) {
        this.attachments = attachments;
    }
}
