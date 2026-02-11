package com.sprta_plan_develop.user.entity;


import com.sprta_plan_develop.plan.entity.Plan;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String useremail;
    private String password;




    public User(String username, String useremail,String password) {
        this.username = username;
        this.useremail = useremail;
        this.password=password;

    }

    public void update(String username, String useremail) {
        this.username = username;
        this.useremail = useremail;
    }




}
