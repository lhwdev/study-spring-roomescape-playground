package roomescape.reservation;

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
	private final Reservations reservations = new Reservations();
	private final AtomicLong nextId = new AtomicLong(0);
	
	@GetMapping
	public ResponseEntity<List<ReservationDto>> getReservations() {
		List<ReservationDto> reservations = this.reservations.getAll().stream()
				.map(ReservationDto::new)
				.toList();
		return ResponseEntity.ok(reservations);
	}
	
	@PostMapping
	public ResponseEntity<ReservationDto> createReservation(@RequestBody CreateReservationBody createReservationBody) {
		ReservationId id = new ReservationId(nextId.incrementAndGet());
		Reservation reservation = createReservationBody.createEntity(id);
		reservations.add(reservation);
		
		return ResponseEntity
				.created(URI.create("/reservations/" + reservation.getId()))
				.body(new ReservationDto(reservation));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
		ReservationId reservationId = new ReservationId(id);
		reservations.remove(reservationId);
		
		return ResponseEntity.noContent().build();
	}
}
