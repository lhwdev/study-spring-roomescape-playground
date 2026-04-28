package roomescape.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public record Reservation(
		int id,
		String name,
		LocalDate date,
		LocalTime time
) {
	public String getDate() {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	public String getTime() {
		return time.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
}
