package com.archymides.userstory.dtos;

import com.archymides.userstory.enums.StoryStatus;
import lombok.Getter;

@Getter
public class StoryReviewDto {
    private StoryStatus status;

}
