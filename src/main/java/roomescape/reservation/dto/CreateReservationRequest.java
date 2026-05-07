package roomescape.reservation.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateReservationRequest(
		@NotNull
		@Length(max = Reservation.NAME_MAX_LENGTH)
		String name,
		
		@Nullable
		LocalDate date,
		
		@Nullable
		LocalTime time
) {
	@AssertTrue(message = "date와 time은 모두 null이거나 모두 값을 가져야 합니다.")
	public boolean isValidDateTime() {
		return (date == null) == (time == null);
	}
	
	public Reservation createEntity(ReservationId id) {
		if(!isValidDateTime()) throw new IllegalStateException();
		
		if(date == null) {
			return Reservation.createNow(id, name);
		} else {
			return Reservation.create(id, name, LocalDateTime.of(date, time));
		}
	}
}
