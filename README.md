# 기능 구현

## 1단계

- [x] 바뀐 API 스펙에 맞게 응답 코드를 수정한다
- [x] 시간 추가에 대한 예외를 처리한다
    - [x] 빈 값 혹은 형식이 다른 시간
    - [x] 중복된 시간 (해당 시간이 이미 존재)
- [x] 시간 삭제에 대한 예외를 처리한다
    - [x] 예약이 존재하는 시간
    - [x] 존재 하지 않는 시간
- [x] 예약 추가에 대한 예외를 처리한다
    - [x] 빈 값 혹은 형식이 다른 예약
    - [x] 중복된 예약 (해당 시간대에 이미 예약이 존재)
    - [x] 지나간 날짜와 시간에 대한 예약
- [x] 예약 삭제에 대한 예외를 처리한다
    - [x] 존재 하지 않는 예약

## 2단계

- [x] 테마 테이블을 생성한다
- [x] 테마 도메인을 추가한다
- [x] 테마 목록 조회 API를 구현한다
- [ ] 테마 추가 API를 구현한다
    - [ ] 빈 값 혹은 형식이 다른 테마
- [ ] 테마 삭제 API를 구현한다
    - [ ] 예약이 존재하는 테마
    - [ ] 존재 하지 않는 테마
- [ ] 예약 도메인에 테마 필드를 추가한다
- [ ] 예약 목록 조회 API에 테마 필드를 추가한다
- [ ] 예약 추가 API에 테마 필드를 추가한다
- [ ] 클라이언트 코드를 수정한다

# API 명세

## 예약

### 예약 목록 조회 API

Request

```
GET /reservations HTTP/1.1
```

Response

```
HTTP/1.1 200
Content-Type: application/json

[
    {
        "id": 1,
        "name": "브라운",
        "date": "2023-08-05",
        "time": {
            "id": 1,
            "startAt": "10:00"
        }
    }
]
```

### 예약 추가 API

Request

```
POST /reservations HTTP/1.1
content-type: application/json

{
    "date": "2023-08-05",
    "name": "브라운",
    "timeId": 1
}
```

Response

```
HTTP/1.1 201
Content-Type: application/json

{
    "id": 1,
    "name": "브라운",
    "date": "2023-08-05",
    "time" : {
        "id": 1,
        "startAt" : "10:00"
    }
}
```

### 예약 삭제 API

Request

```
DELETE /reservations/1 HTTP/1.1
```

Response

```
HTTP/1.1 204
```

## 시간

### 시간 목록 조회 API

Request

```
GET /times HTTP/1.1
```

Response

```
HTTP/1.1 200 
Content-Type: application/json

[
   {
        "id": 1,
        "startAt": "10:00"
    }
]
```

### 시간 추가 API

Request

```
POST /times HTTP/1.1
content-type: application/json

{
    "startAt": "10:00"
}
```

Response

```
HTTP/1.1 201
Content-Type: application/json

{
    "id": 1,
    "startAt": "10:00"
}
```

### 시간 삭제 API

Request

```
DELETE /times/1 HTTP/1.1
```

Response

```
HTTP/1.1 204
```

## 테마

### 테마 목록 조회 API

Request

```
GET /themes HTTP/1.1
```

Response

```
HTTP/1.1 200 
Content-Type: application/json

[
   {
        "id": 1,
        "name": "레벨2 탈출",
        "description": "우테코 레벨2를 탈출하는 내용입니다.",
        "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
    }
]
```

### 테마 추가 API

Request

```
POST /themes HTTP/1.1
content-type: application/json

{
    "name": "레벨2 탈출",
    "description": "우테코 레벨2를 탈출하는 내용입니다.",
    "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
}
```

Response

```
HTTP/1.1 201
Location: /themes/1
Content-Type: application/json

{
    "id": 1,
    "name": "레벨2 탈출",
    "description": "우테코 레벨2를 탈출하는 내용입니다.",
    "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
}

```

### 테마 삭제 API

Request

```
DELETE /themes/1 HTTP/1.1
```

Response

```
HTTP/1.1 204
```
