package com.archymides.userstory.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.archymides.userstory.Exceptions.UnauthorizedException;
import com.archymides.userstory.dtos.LoginDto;
import com.archymides.userstory.dtos.StoryDto;
import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.dtos.UserLoginDto;
import com.archymides.userstory.entities.Story;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.repositories.StoryRepository;
import com.archymides.userstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("StoriesService")
public class StoriesService {

    @Autowired
    private StoryRepository storyRepository;


    @Autowired
    private AuthorizationService authorizationService;


    public void createStory(StoryDto storyDto, String userId) {
        Story story = new Story(storyDto);
        Optional<User> user = authorizationService.getUser(Long.valueOf(userId));
        if(!user.isPresent()) {
            throw new UnauthorizedException();
        }
        story.setUser(user.get());
        storyRepository.save(story);
    }

}
