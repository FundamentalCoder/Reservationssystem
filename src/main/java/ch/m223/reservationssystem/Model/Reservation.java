package ch.m223.reservationssystem.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

    private  Long id;
    private LocalDate datum;
    private LocalTime von;
    private Integer zimmer;
    private String bemerkung;
    private  String privateCode;
    private  String publicCode;

    public void isValid(){

    }
    public void isAvailable(){

    }
}
