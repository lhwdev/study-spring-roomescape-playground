package roomescape.reservation.domain;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Reservation {
	public static final int NAME_MAX_LENGTH = 6;
	
	private final ReservationId id;
	private final String name;
	private final LocalDateTime time;
	
	public Reservation(@Nonnull ReservationId id, @Nonnull String name, @Nonnull LocalDateTime time) {
		Objects.requireNonNull(id, "id가 null일 수 없습니다.");
		Objects.requireNonNull(name, "name이 null일 수 없습니다.");
		Objects.requireNonNull(time, "time이 null일 수 없습니다.");
		
		checkName(name);
		
		this.id = id;
		this.name = name;
		this.time = time;
	}
	
	private void checkName(String name) {
		if(name.length() > NAME_MAX_LENGTH) {
			throw new IllegalArgumentException("이름이 최대 길이를 초과했습니다. 최대 길이는 " + NAME_MAX_LENGTH + "입니다.");
		}
		
		boolean isLetter = name.codePoints().allMatch(Character::isLetter);
		if(!isLetter) {
			throw new IllegalArgumentException("이름에는 숫자, 기호, 띄어쓰기 등을 제외한 글자만 입력할 수 있습니다.");
		}
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
