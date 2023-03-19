package com.blog.service;

import com.blog.dto.VoteDto;
import com.blog.exception.PostNotFoundException;
import com.blog.exception.SpringException;
import com.blog.model.Post;
import com.blog.model.Vote;
import com.blog.repository.PostRepository;
import com.blog.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.blog.model.VoteType.DOWNVOTE;
import static com.blog.model.VoteType.UPVOTE;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private  final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (DOWNVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() != null ? post.getVoteCount() - 1 : -1);
        } else {
            post.setVoteCount(post.getVoteCount() != null ? post.getVoteCount() + 1 : 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();

    }
}
