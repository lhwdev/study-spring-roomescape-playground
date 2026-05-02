package roomescape.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationId;
import roomescape.reservation.dto.CreateReservationBody;
import roomescape.reservation.dto.ReservationDto;
import roomescape.reservation.dto.ReservationTable;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReservationController {
	private final ReservationTable reservationTable = new ReservationTable();
	private final AtomicLong nextId = new AtomicLong(0);
	
	@GetMapping("/reservations")
	public ResponseEntity<List<ReservationDto>> getReservations() {
		List<ReservationDto> reservations = reservationTable.getAll().stream()
				.map(ReservationDto::new)
				.toList();
		return ResponseEntity.ok(reservations);
	}
	
	@PostMapping("/reservations")
	public ResponseEntity<ReservationDto> createReservation(@RequestBody CreateReservationBody createReservationBody) {
		ReservationId id = new ReservationId(nextId.incrementAndGet());
		Reservation reservation = createReservationBody.createEntity(id);
		reservationTable.add(reservation);
		
		return ResponseEntity
				.created(URI.create("/reservations/" + reservation.getId()))
				.body(new ReservationDto(reservation));
	}
	
	@DeleteMapping("/reservations/{id}")
	public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
		ReservationId reservationId = new ReservationId(id);
		reservationTable.remove(reservationId);
		
		return ResponseEntity.noContent().build();
	}
}
