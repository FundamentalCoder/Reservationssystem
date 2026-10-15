package ch.m223.reservationssystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateController {
    @GetMapping("/reservation/private")
    public String privateAnsicht() {
        return "private-reservation";
    }
}
