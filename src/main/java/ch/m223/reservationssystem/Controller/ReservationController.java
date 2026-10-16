package ch.m223.reservationssystem.Controller;

import ch.m223.reservationssystem.Model.Reservation;
import ch.m223.reservationssystem.Model.User;
import ch.m223.reservationssystem.Repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }



    @GetMapping("/reservation/neu")
    public String reservationErfassen(Model model) {

        model.addAttribute("reservation", new Reservation());

        return "reservation-formular";
    }
    @PostMapping("/reservation")
    public String reservationSpeichern(
            @ModelAttribute Reservation reservation,
            @RequestParam String usersText,
            Model model) {

        if (!reservation.isValid()) {
            model.addAttribute(
                    "fehler",
                    "Die eingegebenen Daten sind ungültig."
            );

            return "reservation-formular";
        }

        List<Reservation> bestehendeReservationen =
                reservationRepository.findByDatumAndZimmer(
                        reservation.getDatum(),
                        reservation.getZimmer()
                );

        for (Reservation bestehend : bestehendeReservationen) {

            boolean ueberschneidung =
                    reservation.getVon().isBefore(bestehend.getBis())
                            && reservation.getBis().isAfter(bestehend.getVon());

            if (ueberschneidung) {
                model.addAttribute(
                        "fehler",
                        "Das Zimmer ist zu dieser Zeit bereits reserviert."
                );

                return "reservation-formular";
            }
        }

        String[] personen = usersText.split(",");

        for (String person : personen) {

            String name = person.trim();

            String[] teile = name.split(" ");

            if (teile.length < 2) {
                model.addAttribute(
                        "fehler",
                        "Jeder Teilnehmer braucht Vorname und Nachname."
                );

                return "reservation-formular";
            }

            String vorname = teile[0];
            String nachname = teile[1];

            User user = new User(vorname, nachname);

            reservation.addUser(user);
        }

        reservation.setPrivateCode(UUID.randomUUID().toString());
        reservation.setPublicCode(UUID.randomUUID().toString());

        reservationRepository.save(reservation);

        model.addAttribute("reservation", reservation);

        return "bestaetigung";
    }

}
