package roomescape.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;
import roomescape.reservation.domain.ReservationList;

import java.net.URI;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReservationController {
	private final ReservationList reservationList = new ReservationList();
	private final AtomicLong nextId = new AtomicLong(0);
	
	@GetMapping("/reservations")
	public ResponseEntity<Collection<Reservation>> getReservations() {
		return ResponseEntity.ok(reservationList.get());
	}
	
	@PostMapping("/reservations")
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
		Reservation newReservation = Reservation.toEntity(reservation, new ReservationId(nextId.incrementAndGet()));
		reservationList.add(newReservation);
		
		return ResponseEntity
				.created(URI.create("/reservations/" + newReservation.getId()))
				.body(newReservation);
	}
	
	@DeleteMapping("/reservations/{id}")
	public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
		ReservationId reservationId = new ReservationId(id);
		reservationList.remove(reservationId);
		
		return ResponseEntity.noContent().build();
	}
}
