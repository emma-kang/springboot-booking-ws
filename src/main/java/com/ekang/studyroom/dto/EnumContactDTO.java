package com.ekang.studyroom.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnumContactDTO {
    private int typeId;
    private String description;

    public EnumContactDTO(int typeId, String description) {
        this.typeId = typeId;
        this.description = description;
    }
}
