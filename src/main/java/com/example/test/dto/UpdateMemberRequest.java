package com.example.test.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateMemberRequest {

    private String name;
}
