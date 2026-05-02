package roomescape.reservation.dto;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;

import java.util.*;

public class ReservationTable {
	private final Map<ReservationId, Reservation> reservations = new LinkedHashMap<>();
	
	public Collection<Reservation> getAll() {
		return reservations.values();
	}
	
	public void add(Reservation reservation) {
		reservations.put(reservation.id(), reservation);
	}
	
	public void remove(ReservationId id) {
		reservations.remove(id);
	}
}
