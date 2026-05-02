package roomescape.reservation.domain;

import java.time.LocalDateTime;

public record Reservation(
		ReservationId id,
		String name,
		LocalDateTime time
) {
	public Reservation {
		// 다음 미션에서 예외 구현
		if(name.length() > 128) {
			name = name.substring(0, 128);
		}
		
		LocalDateTime now = LocalDateTime.now();
		if(time.isBefore(now)) {
			time = now;
		}
	}
	
	public long getId() {
		return id.id();
	}
}
