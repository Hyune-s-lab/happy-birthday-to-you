# happy-birthday-to-you

### environment

- spring boot, kotlin
- webflux, spring scheduled
- slack webhook
- docker & docker-compose, shell

### how to run

- `/docker/local/.env` 에 webhook 세팅
- local 실행 `./run_local.sh`
    - `nohup ./run_local.sh &`

## worklog

- [x] [milestone1 사내 생일자 관리를 위한 시스템 구현 및 인프라 구축](https://github.com/Hyune-s-lab/happy-birthday-to-you/wiki/milestone1-%EC%82%AC%EB%82%B4-%EC%83%9D%EC%9D%BC%EC%9E%90-%EA%B4%80%EB%A6%AC%EB%A5%BC-%EC%9C%84%ED%95%9C-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EA%B5%AC%ED%98%84-%EB%B0%8F-%EC%9D%B8%ED%94%84%EB%9D%BC-%EA%B5%AC%EC%B6%95)
- [ ] [milestone2 admin 구현을 위한 사전 준비](https://github.com/Hyune-s-lab/happy-birthday-to-you/wiki/milestone2---admin-%EA%B5%AC%ED%98%84%EC%9D%84-%EC%9C%84%ED%95%9C-%EC%82%AC%EC%A0%84-%EC%A4%80%EB%B9%84)

## feature

> 비개발자도 excel export 등으로 관리할 수 있도록 csv 를 고려합니다.  
> AWS Lambda 를 사용할 수도 있지만, 관리 편의상 최소 비용의 서버를 사용합니다. - AWS Lightsail

### 생일자 입력 api - csv 형태

<img width="200" alt="image" src="https://user-images.githubusercontent.com/55722186/216890218-d4e39ea1-32cc-40bc-8adc-8d813413ec5c.png">

### 생일자 조회 api - json & csv 형태

<img width="200" alt="image" src="https://user-images.githubusercontent.com/55722186/216890479-72064af7-ec08-4f33-a42e-b259691b2286.png">

### 생일자 slack 알림

- 매월 1일 9시에 해당 월 생일자 알림
- 매일 9시에 해당 일 생일자 알림
