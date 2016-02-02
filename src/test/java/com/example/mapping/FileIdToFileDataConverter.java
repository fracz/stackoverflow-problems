package com.example.mapping;

import com.example.mapping.entity.FileDataDto;
import org.bson.types.ObjectId;
import org.dozer.DozerConverter;

public class FileIdToFileDataConverter extends DozerConverter<ObjectId, FileDataDto> {
    public FileIdToFileDataConverter() {
        super(ObjectId.class, FileDataDto.class);
    }

    @Override
    public FileDataDto convertTo(ObjectId source, FileDataDto destination) {
        if (source == null) {
            return null;
        }
        FileDataDto fileData = destination == null ? new FileDataDto() : destination;
        fileData.setId(source.toString());
        // fetch the file from repository and update the name from db
        fileData.setFilename("myfile.txt");
        return fileData;
    }

    @Override
    public ObjectId convertFrom(FileDataDto source, ObjectId destination) {
        return source == null ? null : new ObjectId(source.getId());
    }
}
