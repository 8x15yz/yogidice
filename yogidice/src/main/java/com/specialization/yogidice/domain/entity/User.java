package com.specialization.yogidice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String em;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String pwd;

    String nnm;

    int level;

    boolean em_auth;

    @Generated(GenerationTime.INSERT)
    LocalDateTime join_dt;
    LocalDateTime modify_dt;

    LocalDateTime leave_dt;

    String refresh_tk;



    @Builder
    public User(Long id, String em, String pwd, String nnm, int level, boolean em_auth, LocalDateTime join_dt, LocalDateTime modify_dt, LocalDateTime leave_dt, String refresh_tk) {
        this.id = id;
        this.em = em;
        this.pwd = pwd;
        this.nnm = nnm;
        this.level = level;
        this.em_auth = em_auth;
        this.join_dt = join_dt;
        this.modify_dt = modify_dt;
        this.leave_dt = leave_dt;
        this.refresh_tk = refresh_tk;
    }

}
