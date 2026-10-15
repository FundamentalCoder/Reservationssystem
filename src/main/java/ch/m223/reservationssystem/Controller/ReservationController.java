package ch.m223.reservationssystem.Controller;

import ch.m223.reservationssystem.Model.Reservation;
import ch.m223.reservationssystem.Repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }



    @GetMapping("/reservation/neu")
    public String ReservationErfassen(){
        return "reservation-formular";
    }
    @PostMapping("/reservation")
    public String ReservationSpeichern(@ModelAttribute Reservation reservation, Model model){

        if(!reservation.isValid()){
            model.addAttribute("fehler","Die eingegebenen Daten sind ungültig. ");
            return "reservation-formular";
        }
        String privatCode = UUID.randomUUID().toString();
        String publicCode = UUID.randomUUID().toString();

        reservation.setPrivateCode(privatCode);
        reservation.setPublicCode(publicCode);

        reservationRepository.save(reservation);
        model.addAttribute("reservation",reservation);

        return  "bestaetigung";

    }

}
