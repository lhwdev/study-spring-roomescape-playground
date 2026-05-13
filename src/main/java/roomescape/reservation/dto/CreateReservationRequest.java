package roomescape.reservation.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationException;
import roomescape.reservation.domain.ReservationId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateReservationRequest(
		@NotNull
		@Length(max = Reservation.NAME_MAX_LENGTH)
		String name,
		
		@NotNull
		LocalDate date,
		
		@NotNull
		LocalTime time
) {
	public Reservation createEntity(@Nonnull ReservationId id) throws ReservationException.InputFormat {
		return new Reservation(id, name, LocalDateTime.of(date, time));
	}
}
