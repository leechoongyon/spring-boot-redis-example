package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String name;
    private int age;

    public static MemberDto of(Map<String, Object> result) {
        MemberDto memberDto = MemberDto.builder()
                .name( (String) result.get("name"))
                .build();
        return memberDto;
    }
}
