package roomescape.reservation;

import roomescape.global.exception.ApiException;
import roomescape.global.exception.InternalErrorException;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationException;
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
	
	public ReservationResponse createReservation(CreateReservationRequest request) throws ApiException {
		ReservationId id = new ReservationId(nextId.incrementAndGet());
		Reservation reservation;
		
		try {
			reservation = request.createEntity(id);
		} catch(ReservationException.InputFormat e) {
			throw new ReservationInputFormatException(e.getField(), e.getMessage());
		}
		
		try {
			reservations.add(reservation);
		} catch(ReservationException e) {
			if(e instanceof ReservationException.DuplicateTime)
				throw new ReservationDuplicateTimeException();
			
			throw new InternalErrorException(e);
		}
		
		return ReservationResponse.from(reservation);
	}
	
	public void deleteReservation(ReservationId id) throws ApiException {
		try {
			reservations.remove(id);
		} catch(ReservationException e) {
			if(e instanceof ReservationException.DoesNotExist)
				throw new ReservationDoesNotExistException();
			
			throw new InternalErrorException(e);
		}
	}
}
