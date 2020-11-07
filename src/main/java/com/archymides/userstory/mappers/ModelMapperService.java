package com.archymides.userstory.mappers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.archymides.userstory.dtos.StoryDto;
import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.dtos.UserLoginDto;
import com.archymides.userstory.entities.Story;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.enums.StoryStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("modelMapperService")
public class ModelMapperService {

    private ModelMapper modelMapper;

    public ModelMapperService() {
        this.modelMapper = new ModelMapper();
    }

    public Story map(StoryDto storyDto) {
        Story story = modelMapper.map(storyDto, Story.class);
        story.setStatus(StoryStatus.NEW);
        return story;
    }

    public StoryDto map(Story story) {
        return modelMapper.map(story, StoryDto.class);
    }

    public User map(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(BCrypt.withDefaults().hashToString(12, userDto.getPassword().toCharArray()));
        return user;
    }

    public UserLoginDto map(User user, String token) {
        UserLoginDto userLoginDto = modelMapper.map(user, UserLoginDto.class);
        userLoginDto.setToken(token);
        return userLoginDto;
    }
}
