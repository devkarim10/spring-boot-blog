package com.blog.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {

    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
