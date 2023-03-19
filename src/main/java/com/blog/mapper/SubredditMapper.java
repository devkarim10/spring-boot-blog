package com.blog.mapper;

import com.blog.dto.SubredditDto;
import com.blog.model.Post;
import com.blog.model.Subreddit;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritConfiguration
    @Mapping(target = "posts" , ignore = true)
    Subreddit mapDtoToSubredditEntity(SubredditDto subredditDto);

}
