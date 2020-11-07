package com.archymides.userstory.dtos;

import com.archymides.userstory.entities.Story;
import com.archymides.userstory.enums.Complexity;
import com.archymides.userstory.enums.StoryStatus;
import com.archymides.userstory.enums.StoryType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class StoryDto {
    @NotBlank(message = "Summary is mandatory")
    private String summary;
    @NotBlank(message = "Description is mandatory")
    private String description;
    private StoryType type;
    private Complexity complexity;
    @Min(1)
    private int cost;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StoryStatus status;

    public StoryDto(Story story) {
        this.summary = story.getSummary();
        this.description = story.getDescription();
        this.type = story.getType();
        this.complexity = story.getComplexity();
        this.cost = story.getCost();
        this.id = story.getId();
        this.status = story.getStatus();
    }
}
