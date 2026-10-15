package ch.m223.reservationssystem.Controller;

import ch.m223.reservationssystem.Model.Reservation;
import ch.m223.reservationssystem.Repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String ReservationSpeichern(@ModelAttribute Reservation reservation, Model model){

        if(!reservation.isValid()){
            model.addAttribute("fehler","Die eingegebenen Daten sind ungültig. ");
            return "reservation-formular";
        }
        List<Reservation> bestehendeReservationen = reservationRepository.findByDatumAndZimmer(
                reservation.getDatum(),
                reservation.getZimmer()
        );

        for (Reservation bestehend : bestehendeReservationen){
            boolean ueberschneidung =
                    reservation.getVon().isBefore(bestehend.getBis())
                    &&
                    reservation.getBis().isAfter(bestehend.getVon());
            if(ueberschneidung){
                model.addAttribute(
                        "fehler", "Das Zimmer ist zu dieser Zeit bereits reserviert."
                );
                return "reservation-formular";
            }
        }
        reservation.setPrivateCode( UUID.randomUUID().toString());
        reservation.setPublicCode(UUID.randomUUID().toString());

        reservationRepository.save(reservation);
        model.addAttribute("reservation",reservation);

        return  "bestaetigung";

    }

}
