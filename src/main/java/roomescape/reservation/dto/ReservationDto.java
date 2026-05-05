package roomescape.reservation.dto;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationDto {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("hh:mm");
	
	private final ReservationId id;
	private final String name;
	private final LocalDateTime time;
	
	public ReservationDto(Reservation reservation) {
		this.id = reservation.id();
		this.name = reservation.name();
		this.time = reservation.time();
	}
	
	public long getId() {
		return id.id();
	}
	
	public String getName() {
		return name;
	}
	
	public String getDate() {
		return time.format(DATE_FORMAT);
	}
	
	public String getTime() {
		return time.format(TIME_FORMAT);
	}
}
