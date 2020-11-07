package com.archymides.userstory.entities;

import com.archymides.userstory.dtos.StoryDto;
import com.archymides.userstory.enums.Complexity;
import com.archymides.userstory.enums.StoryStatus;
import com.archymides.userstory.enums.StoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "stories")
@NoArgsConstructor
@Getter
@Setter
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private String description;
    @Enumerated(EnumType.STRING)
    private StoryType type;
    @Enumerated(EnumType.STRING)
    private Complexity complexity;
    private int cost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter
    private User user;
    @Enumerated(EnumType.STRING)
    @Setter
    private StoryStatus status;

    public Story(StoryDto storyDto) {
        this.summary = storyDto.getSummary();
        this.description = storyDto.getDescription();
        this.type = storyDto.getType();
        this.complexity = storyDto.getComplexity();
        this.cost = storyDto.getCost();
        this.status = StoryStatus.NEW;
    }

}
