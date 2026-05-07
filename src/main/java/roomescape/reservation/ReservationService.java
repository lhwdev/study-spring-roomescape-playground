package roomescape.reservation;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;
import roomescape.reservation.domain.Reservations;
import roomescape.reservation.dto.CreateReservationRequest;
import roomescape.reservation.dto.ReservationResponse;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ReservationService {
	private final Reservations reservations = new Reservations();
	private final AtomicLong nextId = new AtomicLong(0);
	
	public List<ReservationResponse> getReservations() {
		return this.reservations.getAll().stream()
				.map(ReservationResponse::from)
				.toList();
	}
	
	public ReservationResponse createReservation(CreateReservationRequest request) {
		ReservationId id = new ReservationId(nextId.incrementAndGet());
		Reservation reservation = request.createEntity(id);
		reservations.add(reservation);
		
		return ReservationResponse.from(reservation);
	}
	
	public void deleteReservation(ReservationId id) {
		reservations.remove(id);
	}
}
