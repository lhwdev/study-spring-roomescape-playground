package roomescape.reservation.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record ReservationId(@JsonValue long id) implements Comparable<ReservationId> {
	@JsonCreator
	public ReservationId {
		if(id <= 0) {
			throw new IllegalArgumentException("예약 번호는 음수나 0일 수 없습니다.");
		}
	}
	
	@Override
	public int compareTo(ReservationId o) {
		return Long.compare(this.id, o.id);
	}
}
