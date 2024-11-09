package org.example.jpaspringboot.repositry;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.jpaspringboot.entity.Post;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomPostRepository {

    private final EntityManager entityManager;

    @Transactional
    public Post saveCustomPost(@NonNull Post post) {
        entityManager.persist(post);
        return post;
    }
}
