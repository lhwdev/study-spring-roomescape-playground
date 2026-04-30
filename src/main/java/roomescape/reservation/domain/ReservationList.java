package roomescape.reservation.domain;

import java.util.*;

public class ReservationList {
	Map<ReservationId, Reservation> reservations = new HashMap<>();
	
	public Collection<Reservation> get() {
		return reservations.values();
	}
	
	public void add(Reservation reservation) {
		reservations.put(reservation.id(), reservation);
	}
	
	public void remove(ReservationId id) {
		reservations.remove(id);
	}
}
