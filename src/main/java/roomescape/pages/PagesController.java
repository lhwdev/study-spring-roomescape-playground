package roomescape.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/reservation")
	public String reservationPage() {
		return "reservation";
	}
}
