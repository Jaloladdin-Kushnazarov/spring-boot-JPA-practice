package org.example.jpaspringboot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.jpaspringboot.entity.Post;
import org.example.jpaspringboot.repositry.PostRepostry;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.text.html.Option;
import java.net.URL;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@SpringBootApplication
@EnableJpaAuditing
public class JpaspringbootApplication {

    private final SessionUser sessionUser;

    public static void main(String[] args) {
        SpringApplication.run(JpaspringbootApplication.class, args);
    }

//    @Bean
    ApplicationRunner runner(PostRepostry postRepostry, ObjectMapper objectMapper) {
        return (args) -> {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            List<Post> posts = objectMapper.readValue(url, new TypeReference<>() {});
            postRepostry.saveAll(posts);
        };
    }

    @Bean
    AuditorAware<Long> auditorAware(SessionUser sessionUser) {
        return ()->
            Optional.ofNullable(sessionUser.getId());
    }

}
