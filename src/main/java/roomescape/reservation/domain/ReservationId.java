package roomescape.reservation.domain;

public record ReservationId(long id) implements Comparable<ReservationId> {
	@Override
	public int compareTo(ReservationId o) {
		return Long.compare(this.id, o.id);
	}
}
