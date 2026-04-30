package roomescape.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record Reservation(
		Long id,
		String name,
		LocalDate date,
		LocalTime time
) {
	public static Reservation toEntity(Reservation reservation, long id) {
		return new Reservation(id, reservation.name, reservation.date, reservation.time);
	}
	
	public String getDate() {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getTime() {
		return time.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
}
