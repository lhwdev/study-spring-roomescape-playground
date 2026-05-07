package roomescape.reservation.domain;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Reservation {
	public static final int NAME_MAX_LENGTH = 6;
	
	public static Reservation create(@Nonnull ReservationId id, @Nonnull String name, @Nonnull LocalDateTime time) {
		LocalDateTime now = LocalDateTime.now();
		if(time.isBefore(now)) {
			throw new IllegalArgumentException("과거에 예약을 추가할 수 없습니다.");
		}
		
		return new Reservation(id, name, time);
	}
	
	public static Reservation createNow(@Nonnull ReservationId id, @Nonnull String name) {
		LocalDateTime now = LocalDateTime.now();
		return new Reservation(id, name, now);
	}
	
	
	private final ReservationId id;
	private final String name;
	private final LocalDateTime time;
	
	private Reservation(ReservationId id, String name, LocalDateTime time) {
		Objects.requireNonNull(id, "id가 null일 수 없습니다.");
		Objects.requireNonNull(name, "name이 null일 수 없습니다.");
		Objects.requireNonNull(time, "time이 null일 수 없습니다.");
		
		if(name.length() > NAME_MAX_LENGTH) {
			throw new IllegalArgumentException("name의 최대 길이를 초과했습니다. 최대 길이는 " + NAME_MAX_LENGTH + "입니다.");
		}
		
		this.id = id;
		this.name = name;
		this.time = time;
	}
	
	public @NotNull ReservationId getId() {
		return id;
	}
	
	public @NotNull String getName() {
		return name;
	}
	
	public @NotNull LocalDateTime getTime() {
		return time;
	}
}
