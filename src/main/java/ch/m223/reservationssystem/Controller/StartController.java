package ch.m223.reservationssystem.Controller;

import ch.m223.reservationssystem.Model.Reservation;
import ch.m223.reservationssystem.Repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class StartController {

    private final ReservationRepository reservationRepository;

    public StartController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/")
    public String startseite() {
        return "index";
    }

    @PostMapping("/code")
    public String codePruefen(
            @RequestParam String code,
            Model model) {

        Optional<Reservation> privateReservation =
                reservationRepository.findByPrivateCode(code);

        if (privateReservation.isPresent()) {
            return "redirect:/private?code=" + code;
        }

        Optional<Reservation> publicReservation =
                reservationRepository.findByPublicCode(code);

        if (publicReservation.isPresent()) {
            return "redirect:/public?code=" + code;
        }

        model.addAttribute(
                "fehler",
                "Der eingegebene Code ist ungültig."
        );

        return "index";
    }
}