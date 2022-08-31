# 실행방법
1.docker-compose -f ./docker-compose.yml up -d
- redis 가 백그라운드로 실행됩니다.

2.java -jar ${APP_HOME}/build/libs/spring-boot-redis-example-1.0-SNAPSHOT.jar
- web application 을 동작시킵니다.

3.캐시를 등록합니다.

```shell
curl --location --request POST 'localhost:8080/api/v1/members' \
--header 'Content-Type: application/json' \
--data-raw '{
	"id" : 1,
    "name": "test22"
}'
```

4. 캐시를 조회합니다.
```shell
curl --location --request GET 'localhost:8080/api/v1/members/1' \
--header 'Content-Type: application/json' \
--data-raw '{
	"id" : 1,
    "name": "test"
}'
```

