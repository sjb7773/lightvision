package com.example.test.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberResponse {
    /*
    * 1. 응답 DTO 새로 생성
    * */
    private Long id;
    private String name;
}
