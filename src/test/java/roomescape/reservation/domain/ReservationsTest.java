package roomescape.reservation.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ReservationsTest {
	Reservation dummy = new Reservation(
			new ReservationId(1),
			"aaa",
			LocalDateTime.of(2026, 5, 7, 11, 13)
	);
	
	Reservation dummy2 = new Reservation(
			new ReservationId(2),
			"bbb",
			LocalDateTime.of(2026, 8, 2, 22, 13)
	);
	
	public ReservationsTest() throws ReservationException {
	}
	
	@Test
	void 예약을_추가_조회할_수_있다() {
		Reservations reservations = new Reservations();
		assertThatCode(() -> {
			reservations.add(dummy);
			reservations.add(dummy2);
		}).doesNotThrowAnyException();
		
		assertThat(reservations.getAll())
				.hasSize(2)
				.isEqualTo(List.of(dummy, dummy2));
	}
	
	@Test
	void 예약을_삭제할_수_있다() throws ReservationException {
		Reservations reservations = new Reservations();
		reservations.add(dummy);
		reservations.add(dummy2);
		
		assertThatCode(() -> reservations.remove(new ReservationId(1)))
				.doesNotThrowAnyException();
		
		assertThat(reservations.getAll()).hasSize(1);
	}
	
	@Test
	void 동일한_예약을_중복으로_추가할_수_없다() throws ReservationException {
		Reservations reservations = new Reservations();
		reservations.add(dummy);
		
		assertThatThrownBy(() -> reservations.add(dummy))
				.isInstanceOf(ReservationException.IdAlreadyExists.class);
	}
	@Test
	void 같은_시간에_예약을_중복으로_추가할_수_없다() throws ReservationException {
		Reservations reservations = new Reservations();
		reservations.add(dummy);
		
		Reservation sameTime = new Reservation(new ReservationId(2), dummy.getName(), dummy.getTime());
		
		assertThatThrownBy(() -> reservations.add(sameTime))
				.isInstanceOf(ReservationException.DuplicateTime.class);
	}
	
	@Test
	void 존재하지_않는_예약을_삭제할_수_없다() {
		Reservations reservations = new Reservations();
		assertThatThrownBy(() -> reservations.remove(new ReservationId(1)))
				.isInstanceOf(ReservationException.DoesNotExist.class);
	}
}
