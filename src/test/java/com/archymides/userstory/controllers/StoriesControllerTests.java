package com.archymides.userstory.controllers;

import com.archymides.userstory.dtos.StoryDto;
import com.archymides.userstory.dtos.StoryReviewDto;
import com.archymides.userstory.enums.StoryStatus;
import com.archymides.userstory.services.StoriesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoriesControllerTests {

    @Mock
    private StoriesService storiesService;

    @InjectMocks
    private StoriesController storiesController;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCallCreateStory() {

        StoryDto storyDto = new StoryDto();
        Principal principal = mock(Principal.class);
        String userId = "userId";
        when(principal.getName()).thenReturn(userId);
        doNothing().when(storiesService).createStory(storyDto, userId);
        storiesController.createStory(storyDto, principal);
        verify(storiesService).createStory(storyDto, userId);
    }

    @Test
    public void shouldCallGetStory() {

        Principal principal = mock(Principal.class);
        String userId = "userId";
        when(principal.getName()).thenReturn(userId);
        ArrayList<StoryDto> stories = new ArrayList<>();
        when(storiesService.getStories(userId)).thenReturn(stories);
        List<StoryDto> receivedStories = storiesController.getStories(principal);
        assertEquals(receivedStories, stories);
        verify(storiesService).getStories(userId);

    }

    @Test
    public void shouldChangeStoryStatus() {

        Principal principal = mock(Principal.class);
        String userId = "userId";
        String storyId = "storyId";
        when(principal.getName()).thenReturn(userId);
        StoryReviewDto storyReviewDto = new StoryReviewDto();
        storyReviewDto.setStatus(StoryStatus.APPROVED);
        doNothing().when(storiesService).reviewStory(userId, storyId, StoryStatus.APPROVED);
        storiesController.reviewStory(storyId, storyReviewDto, principal);
        verify(storiesService).reviewStory(userId, storyId, StoryStatus.APPROVED);

    }
}
