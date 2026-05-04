package roomescape.reservation.domain;

import java.time.LocalDateTime;

public final class Reservation {
	private final ReservationId id;
	private final String name;
	private final LocalDateTime time;
	
	public Reservation(ReservationId id, String name, LocalDateTime time) {
		// 다음 미션에서 예외 구현
		if(name.length() > 128) {
			name = name.substring(0, 128);
		}
		
		LocalDateTime now = LocalDateTime.now();
		if(time.isBefore(now)) {
			time = now;
		}
		this.id = id;
		this.name = name;
		this.time = time;
	}
	
	public ReservationId getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
}
