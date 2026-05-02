package roomescape.reservation.domain;

import java.time.LocalDateTime;

public record Reservation(
		ReservationId id,
		String name,
		LocalDateTime time
) {
	public long getId() {
		return id.id();
	}
}
