package com.blog.mapper;

import com.blog.dto.CommentDto;
import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "text",source = "commentDto.text")
    @Mapping(target = "createdAt" , expression = "java(java.time.Instant.now())")
    @Mapping(target = "post",source = "post")
    Comment mapDtoToEntity(CommentDto commentDto, Post post, User user);


    @Mapping(target = "postId" , expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName" ,expression = "java(comment.getUser().getUsername())")
    CommentDto mapEntityToDto(Comment comment);
}
