package org.example.jpaspringboot.repositry;

import org.example.jpaspringboot.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TodoRepository extends JpaRepository<Todo, Long> {

}
