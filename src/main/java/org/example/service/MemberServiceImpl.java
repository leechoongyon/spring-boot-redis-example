package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.RedisConst;
import org.example.dto.MemberDto;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    @Override
    public void saveMemberTransactionalUsingSessionCallback(MemberDto memberDto) {
        redisTemplate.execute(new SessionCallback<List<Object>>() {
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();

                operations.opsForValue().set(RedisConst.REDIS_MEMBER_KEY_PREFIX + memberDto.getId(), memberDto.getName());
                operations.opsForValue().set(RedisConst.REDIS_MEMBER_KEY_PREFIX + memberDto.getId(), memberDto.getAge());

                return operations.exec();
            }
        });
    }

    @Override
    @Transactional
    public void saveMemberTransactional(MemberDto memberDto) {
        HashOperations<String, Object, Object> hashOperations =
                redisTemplate.opsForHash();
        hashOperations.put(RedisConst.REDIS_MEMBER_KEY_PREFIX + memberDto.getId(),
                "name", memberDto.getName());
        hashOperations.put(RedisConst.REDIS_MEMBER_KEY_PREFIX + memberDto.getId(),
                "age", memberDto.getAge());

//        if (true) {
//            throw new RuntimeException("ERROR");
//        }
    }
}
