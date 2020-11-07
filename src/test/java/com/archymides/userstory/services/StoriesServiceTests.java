package com.archymides.userstory.services;


import com.archymides.userstory.Exceptions.ForbiddenException;
import com.archymides.userstory.Exceptions.ResourceNotFoundException;
import com.archymides.userstory.Exceptions.UnauthorizedException;
import com.archymides.userstory.dtos.LoginDto;
import com.archymides.userstory.dtos.StoryDto;
import com.archymides.userstory.dtos.UserDto;
import com.archymides.userstory.entities.Story;
import com.archymides.userstory.entities.User;
import com.archymides.userstory.enums.Role;
import com.archymides.userstory.enums.StoryStatus;
import com.archymides.userstory.mappers.ModelMapperService;
import com.archymides.userstory.repositories.StoryRepository;
import com.archymides.userstory.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoriesServiceTests {

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private StoryRepository storyRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @InjectMocks
    private StoriesService storiesService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveStoryToRepository() {

        StoryDto storyDto = new StoryDto();
        storyDto.setSummary("summary");
        String userId = "1";

        when(storyRepository.save(any(Story.class))).thenReturn(new Story());
        User user = new User();
        when(authorizationService.getUser(Long.valueOf(userId))).thenReturn(Optional.of(user));
        Story mappedStory = new Story();
        when(modelMapperService.map(storyDto)).thenReturn(mappedStory);
        ArgumentCaptor<Story> argument = ArgumentCaptor.forClass(Story.class);

        storiesService.createStory(storyDto, userId);

        verify(storyRepository).save(argument.capture());
        Story story = argument.getValue();
        assertEquals(mappedStory, story);
        assertEquals(user, story.getUser());
    }

    @Test(expected = UnauthorizedException.class)
    public void ShouldThrowExceptionWhenUserIsEmpty() throws UnauthorizedException {
        StoryDto storyDto = new StoryDto();
        String userId = "1";

        when(authorizationService.getUser(Long.valueOf(userId))).thenReturn(Optional.empty());

        storiesService.createStory(storyDto, userId);

    }

    @Test
    public void shouldGetStoriesOfUserForNonAdmin() {

        Story story = new Story();
        String userId = "1";

        List<Story> stories = Collections.singletonList(story);
        StoryDto mappedStory = new StoryDto();
        when(modelMapperService.map(story)).thenReturn(mappedStory);
        when(storyRepository.findByuserId(Long.valueOf(userId))).thenReturn(stories);
        User user = new User();
        when(authorizationService.getUser(Long.valueOf(userId))).thenReturn(Optional.of(user));

        List<StoryDto> receivedStories = storiesService.getStories(userId);

        verify(storyRepository).findByuserId(Long.valueOf(userId));
        assertEquals(receivedStories.get(0), mappedStory);
    }


    @Test
    public void shouldGetAllStoriesIfUserIsAdmin() {

        Story story = new Story();
        String userId = "1";

        List<Story> stories = Collections.singletonList(story);
        StoryDto mappedStory = new StoryDto();
        when(modelMapperService.map(story)).thenReturn(mappedStory);
        when(storyRepository.findAll()).thenReturn(stories);
        User user = new User();
        user.setRole(Role.ADMIN);
        when(authorizationService.getUser(Long.valueOf(userId))).thenReturn(Optional.of(user));

        List<StoryDto> receivedStories = storiesService.getStories(userId);

        verify(storyRepository).findAll();
        assertEquals(receivedStories.get(0), mappedStory);
    }

    @Test(expected = UnauthorizedException.class)
    public void ShouldThrowExceptionForReviewWhenUserIsEmpty() throws UnauthorizedException {
        String userId = "1";
        String storyId = "id";

        when(authorizationService.getUser(Long.valueOf(userId))).thenReturn(Optional.empty());

        storiesService.reviewStory(userId, storyId, StoryStatus.APPROVED);
    }

    @Test(expected = ForbiddenException.class)
    public void ShouldThrowForbiddenExceptionWhenUserIsNonAdminForReview() throws ForbiddenException {
        String userId = "1";
        String storyId = "id";

        when(authorizationService.getUser(Long.valueOf(userId))).thenReturn(Optional.of(new User()));

        storiesService.reviewStory(userId, storyId, StoryStatus.APPROVED);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void ShouldThrowNotFoundExceptionWhenStoryIsNotFound() throws ResourceNotFoundException {
        String userId = "1";
        String storyId = "2";
        User user = new User();
        user.setRole(Role.ADMIN);

        when(authorizationService.getUser(Long.valueOf(userId))).thenReturn(Optional.of(user));
        when(storyRepository.findById(Long.valueOf(storyId))).thenReturn(Optional.empty());

        storiesService.reviewStory(userId, storyId, StoryStatus.APPROVED);

    }

    @Test
    public void ShouldChangeStoryStatusForReview() {
        String userId = "1";
        String storyId = "2";
        User user = new User();
        user.setRole(Role.ADMIN);
        when(authorizationService.getUser(Long.valueOf(userId))).thenReturn(Optional.of(user));
        when(storyRepository.findById(Long.valueOf(storyId))).thenReturn(Optional.of(new Story()));
        ArgumentCaptor<Story> argument = ArgumentCaptor.forClass(Story.class);
        when(storyRepository.save(any(Story.class))).thenReturn(new Story());

        storiesService.reviewStory(userId, storyId, StoryStatus.APPROVED);

        verify(storyRepository).save(argument.capture());
        Story story = argument.getValue();
        assertEquals(StoryStatus.APPROVED, story.getStatus());

    }


}
