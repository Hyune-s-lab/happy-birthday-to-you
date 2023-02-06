# happy-birthday-to-you

### Environment

- spring boot, kotlin
- webflux, spring scheduled
- docker & docker-compose, shell
- slack webhook

### Feature

- 생일자 관리 - json & csv 형태의 조회 API

<img width="309" alt="image" src="https://user-images.githubusercontent.com/55722186/216890218-d4e39ea1-32cc-40bc-8adc-8d813413ec5c.png">

- 생일자 관리 - csv 형태로 생일자 입력 API

<img width="264" alt="image" src="https://user-images.githubusercontent.com/55722186/216890479-72064af7-ec08-4f33-a42e-b259691b2286.png">

- 생일자 알림
    - 매월 1일 9시에 해당 월 생일자 알림
    - 매일 9시에 해당 일 생일자 알림
    - webhook 을 통한 slack 알림

### How to run

- `/docker/local/.env` 에 webhook 세팅
- local 실행 `./run_local.sh`
    - `nohup ./run_local.sh & `
- swagger 접속 [Link](http://localhost:8080/webjars/swagger-ui/index.html#/)
