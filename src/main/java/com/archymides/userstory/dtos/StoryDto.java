package com.archymides.userstory.dtos;

import com.archymides.userstory.enums.Complexity;
import com.archymides.userstory.enums.StoryType;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class StoryDto {
    @NotBlank(message = "Summary is mandatory")
    private String summary;
    @NotBlank(message = "Description is mandatory")
    private String description;
    private StoryType type;
    private Complexity complexity;
    @Min(1)
    private int cost;
}
