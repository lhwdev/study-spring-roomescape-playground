package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationList;

import java.util.List;

@Controller
public class ReservationController {
	ReservationList reservationList = new ReservationList();
	
	@GetMapping("/reservation")
	public String reservationPage() {
		return "reservation";
	}
	
	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation>> getReservations() {
		return ResponseEntity.ok(reservationList.getReservations());
	}
}
