package roomescape.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;
import roomescape.reservation.dto.CreateReservationBody;
import roomescape.reservation.dto.ReservationDto;
import roomescape.reservation.domain.Reservations;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	private final ReservationService service = new ReservationService();
	
	@GetMapping
	public List<ReservationDto> getReservations() {
		return service.getReservations();
	}
	
	@PostMapping
	public ResponseEntity<ReservationDto> createReservation(@RequestBody CreateReservationBody createReservationBody) {
		ReservationDto result = service.createReservation(createReservationBody);
		
		return ResponseEntity
				.created(URI.create("/reservations/" + result.id().id()))
				.body(result);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReservation(@PathVariable long id) {
		ReservationId reservationId = new ReservationId(id);
		service.deleteReservation(reservationId);
	}
}
