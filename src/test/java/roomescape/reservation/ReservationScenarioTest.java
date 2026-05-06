package roomescape.reservation;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import roomescape.reservation.domain.ReservationId;
import roomescape.reservation.dto.ReservationDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationScenarioTest {
	Map<String, String> createParams;
	ReservationDto expected;
	
	ReservationScenarioTest() {
		createParams = new HashMap<>();
		createParams.put("name", "브라운");
		createParams.put("date", LocalDate.now().plusDays(5).format(DateTimeFormatter.ISO_LOCAL_DATE));
		createParams.put("time", "15:40");
		
		expected = new ReservationDto(
				new ReservationId(1),
				createParams.get("name"),
				LocalDate.parse(createParams.get("date")),
				LocalTime.parse(createParams.get("time"))
		);
		
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
	}
	
	@Test
	@Order(1)
	void 예약_생성() {
		ReservationDto created = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(createParams)
				.when().post("/reservations")
				.then()
				.statusCode(201)
				.header("Location", "/reservations/1")
				.extract().as(ReservationDto.class);
		
		assertThat(created).isEqualTo(expected);
	}
	
	@Test
	@Order(2)
	void 예약_조회() {
		ReservationDto[] current = RestAssured.given()
				.when().get("/reservations")
				.then()
				.statusCode(200)
				.extract().as(ReservationDto[].class);
		
		assertThat(current)
				.singleElement()
				.isEqualTo(expected);
	}
	
	@Test
	@Order(3)
	void 예약_삭제() {
		RestAssured.given()
				.when().delete("/reservations/1")
				.then()
				.statusCode(204);
		
		RestAssured.given()
				.when().get("/reservations")
				.then()
				.statusCode(200)
				.body("size()", is(0));
	}
}
