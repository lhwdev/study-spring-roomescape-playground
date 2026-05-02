package roomescape.reservation.dto;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;

import java.util.*;

public class ReservationTable {
	private final Map<ReservationId, Reservation> reservations = new LinkedHashMap<>();
	
	public synchronized List<Reservation> getAll() {
		return new ArrayList<>(reservations.values());
	}
	
	public synchronized void add(Reservation reservation) {
		reservations.put(reservation.id(), reservation);
	}
	
	public synchronized void remove(ReservationId id) {
		reservations.remove(id);
	}
}
