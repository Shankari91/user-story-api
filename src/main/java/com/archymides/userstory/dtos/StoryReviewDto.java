package com.archymides.userstory.dtos;

import com.archymides.userstory.enums.StoryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryReviewDto {
    private StoryStatus status;

}
