package com.archymides.userstory.services;

import com.archymides.userstory.Exceptions.ForbiddenException;
import com.archymides.userstory.Exceptions.ResourceNotFoundException;
import com.archymides.userstory.Exceptions.UnauthorizedException;
import com.archymides.userstory.dtos.StoryDto;
import com.archymides.userstory.entities.Story;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.enums.StoryStatus;
import com.archymides.userstory.mappers.ModelMapperService;
import com.archymides.userstory.repositories.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("StoriesService")
public class StoriesService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private ModelMapperService modelMapper;


    public void createStory(StoryDto storyDto, String userId) {
        Story story = modelMapper.map(storyDto);
        Optional<User> user = getUserById(userId);
        story.setUser(user.get());
        storyRepository.save(story);
    }

    public List<StoryDto> getStories(String userId) {
        Optional<User> user = getUserById(userId);
        if (user.get().isAdmin()) {
            return storyRepository.findAll().stream().map(story -> modelMapper.map(story)).collect(Collectors.toList());
        }
        return storyRepository.findByuserId(Long.valueOf(userId)).stream().map(story -> modelMapper.map(story)).collect(Collectors.toList());
    }

    public void reviewStory(String userId, String storyId, StoryStatus status) {
        Optional<User> user = getUserById(userId);
        if (!user.get().isAdmin()) {
            throw new ForbiddenException();
        }
        Optional<Story> story = storyRepository.findById(Long.valueOf(storyId));
        if(!story.isPresent()) {
            throw new ResourceNotFoundException();
        }
        story.get().setStatus(status);
        storyRepository.save(story.get());
    }

    private Optional<User> getUserById(String userId) {
        Optional<User> user = authorizationService.getUser(Long.valueOf(userId));
        if (!user.isPresent()) {
            throw new UnauthorizedException();
        }
        return user;
    }

}
