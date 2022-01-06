package org.example.api;

import lombok.RequiredArgsConstructor;
import org.example.dto.MemberDto;
import org.example.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/member/{id}")
    public ResponseEntity<?> getMembersUsingRedis(@PathVariable("id") String id) {
        return ResponseEntity.ok(memberService.getMembersUsingRedis(id));
    }

    @PostMapping("/api/v1/member")
    public ResponseEntity<?> saveMemberUsingRedis(@RequestBody MemberDto memberDto) {
        memberService.saveMemberUsingRedis(memberDto);
        return ResponseEntity.ok().build();
    }
}
