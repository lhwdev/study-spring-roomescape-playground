package roomescape.reservation.domain;

public class ReservationException extends Exception {
	public ReservationException(String message) {
		super(message);
	}
	
	public static class IdAlreadyExists extends ReservationException {
		public IdAlreadyExists() {
			super("이미 존재하는 예약번호입니다.");
		}
	}
	
	public static class DuplicateTime extends ReservationException {
		public DuplicateTime() {
			super("해당 시간에 다른 예약이 이미 존재합니다.");
		}
	}
}
