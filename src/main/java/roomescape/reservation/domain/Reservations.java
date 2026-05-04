package roomescape.reservation.domain;

import java.util.*;

public class Reservations {
	private final Map<ReservationId, Reservation> reservations = new TreeMap<>();
	
	public synchronized List<Reservation> getAll() {
		return new ArrayList<>(reservations.values());
	}
	
	public synchronized void add(Reservation reservation) {
		reservations.put(reservation.getId(), reservation);
	}
	
	public synchronized void remove(ReservationId id) {
		reservations.remove(id);
	}
}
