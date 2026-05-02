package roomescape.reservation.dto;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateReservationBody(
		String name,
		LocalDate date,
		LocalTime time
) {
	public Reservation createEntity(ReservationId id) {
		return new Reservation(
				id,
				name,
				LocalDateTime.of(date, time)
		);
	}
}
