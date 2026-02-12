package com.sprta_plan_develop.plan.entity;

import com.sprta_plan_develop.global.config.BaseEntity;
import com.sprta_plan_develop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "plans")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private String password;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Plan(String title, String content,User user){
        this.title=title;
        this.content=content;
        this.user=user;

    }

    public void planUpdate(String title ,String content){
        this.title=title;
        this.content=content;


    }
}
