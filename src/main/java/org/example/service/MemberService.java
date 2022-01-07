package org.example.service;

import org.example.dto.MemberDto;

public interface MemberService {
    MemberDto getMembersUsingRedis(String id);
    void saveMemberUsingRedis(MemberDto request);

    void saveMemberTransactionalUsingSessionCallback(MemberDto memberDto);

    void saveMemberTransactional(MemberDto memberDto);
}
