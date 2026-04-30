package roomescape.domain;

import java.util.*;

public class ReservationList {
	Map<Long, Reservation> reservations = new HashMap<>();
	
	public Collection<Reservation> get() {
		return reservations.values();
	}
	
	public void add(Reservation reservation) {
		reservations.put(reservation.id(), reservation);
	}
	
	public void remove(long id) {
		reservations.remove(id);
	}
}
