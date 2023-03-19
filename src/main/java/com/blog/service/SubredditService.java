package com.blog.service;

import com.blog.dto.SubredditDto;
import com.blog.exception.SpringException;
import com.blog.mapper.SubredditMapper;
import com.blog.model.Subreddit;
import com.blog.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubredditEntity(subredditDto));
        subredditDto.setId(subreddit.getId());

        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll().stream().map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    public SubredditDto getSubredditById(Long id){
        Subreddit subreddit = subredditRepository.findById(id).
                orElseThrow(()->new SpringException("No subreddit found with id" + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }


}
