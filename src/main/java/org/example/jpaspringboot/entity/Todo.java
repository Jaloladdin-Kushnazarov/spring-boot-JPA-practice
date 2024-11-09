package org.example.jpaspringboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


//
//    //bunday qilish aslida xato
//    @PrePersist
//    public void onPrePersist() {
//        this.updatedBy = null;
//    }
//
//    @PreUpdate
//    public void onPreUpdate() {
//        this.updatedBy = getSessionUserId();
//    }
//
//    private Long getSessionUserId() {
//        return new Random().nextLong(10, 20);
//    }
}