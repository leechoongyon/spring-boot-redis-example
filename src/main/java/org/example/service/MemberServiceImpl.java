package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.RedisConst;
import org.example.dto.MemberDto;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final RedisTemplate redisTemplate;

    private final String KEY = "member-1";

    @Override
    public MemberDto getMembersUsingRedis(String id) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, Object> result = hashOperations.entries(RedisConst.REDIS_MEMBER_KEY_PREFIX + id);
        return MemberDto.of(result);
    }

    @Override
    public void saveMemberUsingRedis(MemberDto request) {
        HashOperations<String, Object, Object> hashOperations =
                redisTemplate.opsForHash();
        hashOperations.put(RedisConst.REDIS_MEMBER_KEY_PREFIX + request.getId(), "name", request.getName());
    }
}
