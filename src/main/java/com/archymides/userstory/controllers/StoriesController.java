package com.archymides.userstory.controllers;

import com.archymides.userstory.dtos.StoryDto;
import com.archymides.userstory.services.StoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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
}
