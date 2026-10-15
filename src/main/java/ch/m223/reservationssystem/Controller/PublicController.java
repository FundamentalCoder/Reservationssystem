package ch.m223.reservationssystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {
    @GetMapping("/reservation/public")
    public String publicAnsicht() {
        return "public-reservation";
    }
}
