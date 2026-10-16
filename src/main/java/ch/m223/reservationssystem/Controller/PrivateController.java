package ch.m223.reservationssystem.Controller;

import ch.m223.reservationssystem.Model.Reservation;
import ch.m223.reservationssystem.Repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class PrivateController {

    private final ReservationRepository reservationRepository;

    public PrivateController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/private")
    public String privateReservation(
            @RequestParam String code,
            Model model) {

        Optional<Reservation> reservation =
                reservationRepository.findByPrivateCode(code);

        if (reservation.isEmpty()) {
            model.addAttribute("fehler", "Private Code ist ungültig.");
            return "index";
        }

        model.addAttribute("reservation", reservation.get());

        return "private-reservation";
    }


    @GetMapping("/private/bearbeiten")
    public String bearbeiten(
            @RequestParam String code,
            Model model) {

        Optional<Reservation> reservation =
                reservationRepository.findByPrivateCode(code);

        if (reservation.isEmpty()) {
            model.addAttribute("fehler", "Private Code ist ungültig.");
            return "index";
        }

        model.addAttribute("reservation", reservation.get());

        return "reservation-bearbeiten";
    }


    @PostMapping("/private/bearbeiten")
    public String bearbeitenSpeichern(
            @ModelAttribute Reservation reservation,
            Model model) {

        Optional<Reservation> bestehendOptional =
                reservationRepository.findById(reservation.getId());

        if (bestehendOptional.isEmpty()) {
            model.addAttribute("fehler", "Reservation wurde nicht gefunden.");
            return "index";
        }

        Reservation bestehend = bestehendOptional.get();

        bestehend.setDatum(reservation.getDatum());
        bestehend.setVon(reservation.getVon());
        bestehend.setBis(reservation.getBis());
        bestehend.setZimmer(reservation.getZimmer());
        bestehend.setBemerkung(reservation.getBemerkung());

        if (!bestehend.isValid()) {
            model.addAttribute("reservation", bestehend);
            model.addAttribute(
                    "fehler",
                    "Die eingegebenen Daten sind ungültig."
            );

            return "reservation-bearbeiten";
        }

        reservationRepository.save(bestehend);

        model.addAttribute("reservation", bestehend);

        return "private-reservation";
    }


    @PostMapping("/private/loeschen")
    public String deleteReservation(
            @RequestParam String code,
            Model model) {

        Optional<Reservation> reservation =
                reservationRepository.findByPrivateCode(code);

        if (reservation.isEmpty()) {
            model.addAttribute("fehler", "Private Code ist ungültig.");
            return "index";
        }

        reservationRepository.delete(reservation.get());

        model.addAttribute(
                "meldung",
                "Reservation wurde gelöscht."
        );

        return "index";
    }
}