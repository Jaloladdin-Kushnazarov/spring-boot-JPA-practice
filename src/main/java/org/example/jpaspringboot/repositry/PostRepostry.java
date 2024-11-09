package org.example.jpaspringboot.repositry;

import org.example.jpaspringboot.entity.Post;
import org.example.jpaspringboot.projection.PostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;

public interface PostRepostry extends JpaRepository<Post, Long> {

    List<Post> findAllByUserId(Integer userId);

//    @Transactional(readOnly = true)
    @Query("from Post p where p.userId in (?1)")
    List<Post> findAllPostsByUserIds(Collection<Integer> userIDs);

    @Query("from Post p where p.userId in (?1)")
    List<PostProjection> findAllPostProjectionsByUserIds(Collection<Integer> userIDs);
}