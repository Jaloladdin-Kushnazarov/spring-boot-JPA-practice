package org.example.jpaspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpaspringboot.entity.Post;
import org.example.jpaspringboot.projection.PostProjection;
import org.example.jpaspringboot.repositry.CustomPostRepository;
import org.example.jpaspringboot.repositry.PostRepostry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepostry postRepostry;
    private final CustomPostRepository customPostRepository;


    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postRepostry.findAll();
    }


    @GetMapping("/pageed")
    public Page<Post> getPosts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, size, sort);
        return postRepostry.findAll(pageable);
    }


    @GetMapping("/byusers/{userIDs}")
    public List<Post> getAllPosts(@PathVariable Collection<Integer> userIDs) {
        return postRepostry.findAllPostsByUserIds(userIDs);
    }

    @GetMapping("/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Integer userId) {
        List<Post> posts = postRepostry.findAllByUserId(userId);
        return posts;
    }


    @GetMapping("/sorted")
    public List<Post> getPostsByUserId() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "userId")
//                .and(Sort.by(Sort.Direction.ASC, "id"));

        Sort sort = Sort.by(Sort.Order.desc("userId"), Sort.Order.asc("id"));
        List<Post> posts = postRepostry.findAll(sort);
        return posts;
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer userId) {
        List<Post> posts = postRepostry.findAllByUserId(userId);
        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        postRepostry.deleteAll(posts);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public Post save(@RequestBody Post post) {
        return customPostRepository.saveCustomPost(post);
    }


    @GetMapping("/projection/{userIds}")
    public List<PostProjection> getPostProjection(@PathVariable Collection<Integer> userIds) {
        List<PostProjection> projections = postRepostry.findAllPostProjectionsByUserIds(userIds);
        return projections;

    }

}
