package com.example.mapping;

import com.example.mapping.entity.FileDataDto;
import com.example.mapping.entity.MyEntity;
import com.example.mapping.entity.MyEntityDto;
import org.bson.types.ObjectId;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.dozer.loader.api.FieldsMappingOptions.*;
import static org.junit.Assert.assertEquals;

public class DozerMappingImmutableTest {

    private DozerBeanMapper mapper = new DozerBeanMapper();

    @Before
    public void setupMapper() {
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(MyEntity.class, MyEntityDto.class)
                        .fields("attachmentIds", "attachments", hintA(ObjectId.class), hintB(FileDataDto.class));
                mapping(ObjectId.class, FileDataDto.class)
                        .fields(this_(), this_(), customConverter(FileIdToFileDataConverter.class));
            }
        });
    }

    @Test
    public void myEntityToDto() {
        MyEntity myEntity = new MyEntity();
        myEntity.setAttachmentIds(Arrays.asList(new ObjectId()));
        MyEntityDto dto = mapper.map(myEntity, MyEntityDto.class);
        assertEquals(1, dto.getAttachments().size());
        assertEquals(myEntity.getAttachmentIds().get(0).toString(), dto.getAttachments().get(0).getId());
        assertEquals("myfile.txt", dto.getAttachments().get(0).getFilename());
    }

    @Test
    public void dtoToMyEntity() {
        MyEntityDto dto = new MyEntityDto();
        FileDataDto fileData = new FileDataDto();
        fileData.setFilename("file.txt");
        fileData.setId(new ObjectId().toString());
        dto.setAttachments(Arrays.asList(fileData));
        MyEntity myEntity = mapper.map(dto, MyEntity.class);
        assertEquals(1, myEntity.getAttachmentIds().size());
        assertEquals(fileData.getId(), myEntity.getAttachmentIds().get(0).toString());
    }

}
