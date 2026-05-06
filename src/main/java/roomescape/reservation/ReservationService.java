package roomescape.reservation;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;
import roomescape.reservation.domain.Reservations;
import roomescape.reservation.dto.CreateReservationBody;
import roomescape.reservation.dto.ReservationDto;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ReservationService {
	private final Reservations reservations = new Reservations();
	private final AtomicLong nextId = new AtomicLong(0);
	
	public List<ReservationDto> getReservations() {
		return this.reservations.getAll().stream()
				.map(ReservationDto::from)
				.toList();
	}
	
	public ReservationDto createReservation(CreateReservationBody createReservationBody) {
		ReservationId id = new ReservationId(nextId.incrementAndGet());
		Reservation reservation = createReservationBody.createEntity(id);
		reservations.add(reservation);
		
		return ReservationDto.from(reservation);
	}
	
	public void deleteReservation(ReservationId id) {
		reservations.remove(id);
	}
}
