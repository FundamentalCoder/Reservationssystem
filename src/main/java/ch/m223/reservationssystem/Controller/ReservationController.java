package ch.m223.reservationssystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
    @GetMapping("/reservation/neu")
    public String ReservationErfassen(){
        return "reservation-formular";
    }
}
