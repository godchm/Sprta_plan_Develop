package com.sprta_plan_develop.comment.entity;

import com.sprta_plan_develop.plan.entity.BaseEntity;
import com.sprta_plan_develop.plan.entity.Plan;
import com.sprta_plan_develop.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

    @Getter
    @Entity
    @Table(name = "comments")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String content;



        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "plan_id")
        private Plan plan;




        public Comment( String content,User user, Plan plan) {
            this.content = content;
            this.user=user;
            this.plan=plan;


        }

        public void CommentUpdate( String content) {
            this.content = content;


        }
    }
