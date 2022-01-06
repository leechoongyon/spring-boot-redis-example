package org.example.service;

import org.example.dto.MemberDto;

public interface MemberService {
    MemberDto getMembersUsingRedis(String id);
    void saveMemberUsingRedis(MemberDto request);
}
