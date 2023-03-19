package com.blog.mapper;

import com.blog.dto.PostRequestDto;
import com.blog.dto.PostResponseDto;
import com.blog.model.Post;
import com.blog.model.Subreddit;
import com.blog.model.User;
import com.blog.repository.CommentRepository;
import com.blog.repository.VoteRepository;
import com.blog.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    public PostMapper() {
    }

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequestDto.description")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post mapDtoToEntity(PostRequestDto postRequestDto, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponseDto mapEntityToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedAt().toEpochMilli());
    }

}
