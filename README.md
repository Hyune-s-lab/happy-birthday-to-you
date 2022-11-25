# happy-birthday-to-you

### Environment

- spring boot, kotlin
- webflux, spring scheduled
- docker & docker-compose, shell
- slack webhook

### Feature

- 생일자 관리
    - csv 파일을 활용한 초기 세팅
    - json & csv 형태로 API 조회
- 생일자 알림
    - 매월 1일 9시에 해당 월 생일자 알림
    - 매일 9시에 해당 일 생일자 알림
    - webhook 을 통한 slack 알림

### How to run

- `/docker/local/.env` 에 webhook 세팅
- local 실행 `./run_local.sh`
- swagger 접속 [Link](http://localhost:8080/webjars/swagger-ui/index.html#/)
