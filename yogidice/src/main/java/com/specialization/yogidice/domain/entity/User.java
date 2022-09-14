package com.specialization.yogidice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.specialization.yogidice.common.util.ReviewedType;
import com.specialization.yogidice.common.util.RoleType;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 유저 모델 정의.
 */
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    private String kakao_id;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Enumerated(EnumType.STRING)
    private ReviewedType reviewed;
    @Builder
    public User(String kakao_id, String nickname, RoleType role, ReviewedType reviewed) {
        this.kakao_id = kakao_id;
        this.nickname = nickname;
        this.role = role;
        this.reviewed = reviewed;
    }

    public static User create(String kakao_id, String nickname, RoleType role, ReviewedType reviewed ){
        User user = new User();
        user.kakao_id = kakao_id;
        user.nickname = nickname;
        user.role = role;
        user.reviewed = reviewed;
        return user;
    }

    public void update(String kakao_id, String nickname, RoleType role, ReviewedType reviewed ){
        this.kakao_id = kakao_id;
        this.nickname = nickname;
        this.role = role;
        this.reviewed = reviewed;
    }

}
