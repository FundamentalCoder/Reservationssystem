package ch.m223.reservationssystem.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "reservationen")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private LocalDate datum;
    private LocalTime von;
    private Integer zimmer;
    private String bemerkung;
    private  String privateCode;
    private  String publicCode;

    public Reservation(){}

    public Reservation
            (LocalDate datum,
             LocalTime von,
             Integer zimmer,
             String bemerkung,
             String privateCode,
             String publicCode) {

        this.datum = datum;
        this.von = von;
        this.zimmer = zimmer;
        this.bemerkung = bemerkung;
        this.privateCode = privateCode;
        this.publicCode = publicCode;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    public LocalTime getVon() {
        return von;
    }
    public void setVon(LocalTime von) {
        this.von = von;
    }
    public Integer getZimmer() {
        return zimmer;
    }
    public void setZimmer(Integer zimmer) {
        this.zimmer = zimmer;
    }
    public String getBemerkung() {
        return bemerkung;
    }
    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }
    public String getPrivateCode() {
        return privateCode;
    }
    public void setPrivateCode(String privateCode) {
        this.privateCode = privateCode;
    }
    public String getPublicCode() {
        return publicCode;
    }
    public void setPublicCode(String publicCode) {
        this.publicCode = publicCode;}

    public void isValid(){

    }
    public void isAvailable(){

    }
}
