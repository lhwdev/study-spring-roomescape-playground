# 방탈출 관리 애플리케이션

## 기능 요구사항

### `GET /`

어드민 메인 페이지 표시

### `GET /reservation`
예약 페이지 표시

### `GET /reservations`
```http request
HTTP/1.1 200
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
