package ch.m223.reservationssystem.Controller;

import ch.m223.reservationssystem.Model.Reservation;

import ch.m223.reservationssystem.Repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PublicController {

    private final ReservationRepository reservationRepository;

    public PublicController(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/public")
    public String publicAnsicht(
            @RequestParam String code,
            Model model) {

        Optional<Reservation> reservation =
                reservationRepository.findByPublicCode(code);

        if (reservation.isEmpty()) {
            model.addAttribute("fehler", "Public Code ist ungültig.");
            return "index";
        }

        model.addAttribute("reservation", reservation.get());

        return "public-reservation";
    }



}
