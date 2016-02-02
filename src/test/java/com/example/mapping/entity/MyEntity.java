package com.example.mapping.entity;

import org.bson.types.ObjectId;

import java.util.List;

public class MyEntity {
    private List<ObjectId> attachmentIds;

    public List<ObjectId> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<ObjectId> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
