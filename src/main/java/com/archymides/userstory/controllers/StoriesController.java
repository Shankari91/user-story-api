package com.archymides.userstory.controllers;

import com.archymides.userstory.dtos.StoryDto;
import com.archymides.userstory.dtos.StoryReviewDto;
import com.archymides.userstory.services.StoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@Validated
public class StoriesController {

    @Autowired
    private StoriesService storiesService;


    @RequestMapping(value = "/stories", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStory(@Valid @RequestBody StoryDto storyDto, Principal principal) {
        storiesService.createStory(storyDto, principal.getName());
    }

    @RequestMapping(value = "/stories", method = RequestMethod.GET)
    public List<StoryDto> getStories(Principal principal) {
        return storiesService.getStories(principal.getName());
    }


    //if admin wants to change status alone he can use patch. no valid annotation here
    @RequestMapping(value = "/stories/{id}/review", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void reviewStory(@PathVariable("id") String storyId, @Valid @RequestBody StoryReviewDto storyReviewDto, Principal principal) {
        storiesService.reviewStory(principal.getName(), storyId, storyReviewDto.getStatus());
    }
}
