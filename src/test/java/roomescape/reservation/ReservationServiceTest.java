package roomescape.reservation;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import roomescape.global.exception.ApiException;
import roomescape.reservation.domain.ReservationId;
import roomescape.reservation.dto.CreateReservationRequest;
import roomescape.reservation.dto.ReservationResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ReservationServiceTest {
	CreateReservationRequest dummyCreateRequest = new CreateReservationRequest(
			"이현우",
			LocalDate.of(2027, 3, 20),
			LocalTime.of(10, 11)
	);
	
	CreateReservationRequest dummyCreateRequest2 = new CreateReservationRequest(
			"우끾끾",
			LocalDate.of(2027, 4, 21),
			LocalTime.of(23, 50)
	);
	
	@Test
	void 예약_생성_가능() {
		ReservationService service = new ReservationService();
		assertThatCode(() -> {
			ReservationResponse response = service.createReservation(dummyCreateRequest);
			
			assertThat(response.id()).isEqualTo(new ReservationId(1));
			assertThat(response.name()).isEqualTo("이현우");
		}).doesNotThrowAnyException();
	}
	
	@Test
	void 예약_조회_가능() throws ApiException {
		ReservationService service = new ReservationService();
		service.createReservation(dummyCreateRequest);
		service.createReservation(dummyCreateRequest2);
		
		List<ReservationResponse> reservations = service.getReservations();
		assertThat(reservations).hasSize(2);
		assertThat(reservations.get(0).name()).isEqualTo("이현우");
		assertThat(reservations.get(1).name()).isEqualTo("우끾끾");
	}
	
	@Test
	void 예약_삭제_가능() throws ApiException {
		ReservationService service = new ReservationService();
		service.createReservation(dummyCreateRequest);
		
		assertThatCode(() -> service.deleteReservation(new ReservationId(1)))
				.doesNotThrowAnyException();
		
		assertThat(service.getReservations())
				.hasSize(0);
	}
	
	@Test
	void 동일_시간에_중복_예약_불가능() throws ApiException {
		ReservationService service = new ReservationService();
		service.createReservation(dummyCreateRequest);
		
		assertThatThrownBy(() -> service.createReservation(dummyCreateRequest))
				.isInstanceOf(ReservationDuplicateTimeException.class);
	}
	
	@Test
	void 존재하지_않는_예약_삭제_불가능() {
		ReservationService service = new ReservationService();
		assertThatThrownBy(() -> service.deleteReservation(new ReservationId(1)))
				.isInstanceOf(ReservationDoesNotExistException.class);
	}
}
