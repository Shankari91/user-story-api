package com.archymides.userstory.repositories;

import com.archymides.userstory.entities.Story;
import com.archymides.userstory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("storyRepository")
public interface StoryRepository extends JpaRepository<Story, Long> {

}