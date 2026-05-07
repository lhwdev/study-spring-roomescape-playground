# 방탈출 관리 애플리케이션

## 공통

****

## HTML 페이지 표시

- `GET /`
- `GET /reservation`

## `GET /reservations`

전체 예약 목록을 반환합니다.

> `() -> ReservationDto[]`

**Response**
```http request
HTTP 200
Content-Type: application/json

[
    {
        "id": 1,
        "name": "브라운",
        "date": "2023-01-01",
        "time": "10:00"
    },
    {
        "id": 2,
        "name": "브라운",
        "date": "2023-01-02",
        "time": "11:00"
    }
]
```

## `POST /reservations`

새로운 예약을 추가합니다.

> `(body: CreateReservationBody) -> ReservationDto`

- 지정되는 시간은 과거일 수 없습니다.

### 추후 예약 추가

- 현재로부터 대략 1분 이내에는 예약을 추가할 수 없습니다. 대신 [지금 예약 추가](#지금-예약-추가)를 참고해주세요.

**Request**
```http request
POST /reservations
Content-Type: application/json

{
    "name": "브라운",
    "date": "2023-08-05",
    "time": "15:40"
}
```

**Response**
```http request
HTTP 201 
Location: /reservations/1
Content-Type: application/json

{
    "id": 1,
    "name": "브라운",
    "date": "2023-08-05",
    "time": "15:40"
}
```

### 지금 예약 추가

**Request**
```http request
POST /reservations
Content-Type: application/json

{
    "name": "브라운"
}
```

**Response**
```http request
HTTP 201 
Location: /reservations/1
Content-Type: application/json

{
    "id": 1,
    "name": "브라운",
    "date": "2026-05-07", // 현재 날짜
    "time": "10:59" // 현재 시간
}
```

## `DELETE /reservations/{id}`

해당 id를 가진 예약을 삭제합니다.

> `(pathId: ReservationId) -> void`

**Response**
```http request
HTTP 204 No Content
```
