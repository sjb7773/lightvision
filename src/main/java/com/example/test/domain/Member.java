package com.example.test.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Table(schema = "public", name = "account")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Member {

    /*
    *
    * 1. component annotation 삭제
    * 2. NoArgsConstructor, AllArgsConstructor annotation 추가
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

}
