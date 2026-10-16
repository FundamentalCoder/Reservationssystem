package ch.m223.reservationssystem.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservationen")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private LocalDate datum;
    private LocalTime von;
    private LocalTime bis;
    private Integer zimmer;
    private String bemerkung;
    private  String privateCode;
    private  String publicCode;

    @OneToMany(mappedBy = "reservation",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    public Reservation(){}



    public Reservation
            (LocalDate datum,
             LocalTime von,
             LocalTime bis,
             Integer zimmer,
             String bemerkung,
             String privateCode,
             String publicCode) {

        this.datum = datum;
        this.von = von;
        this.bis = bis;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LocalTime getBis() {
        return bis;
    }

    public void setBis(LocalTime bis) {
        this.bis = bis;
    }

    public boolean isValid() {
        if (datum == null || von == null || bis == null) {
            return false;
        }

        if (!datum.isAfter(LocalDate.now())) {
            return false;
        }

        if (!von.isBefore(bis)) {
            return false;
        }

        if (zimmer == null || zimmer < 101 || zimmer > 105) {
            return false;
        }

        if (bemerkung == null ||
                bemerkung.length() < 10 ||
                bemerkung.length() > 200) {
            return false;
        }

        return true;

    }

    public void addUser(User user) {
        this.users.add(user);
        user.setReservation(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.setReservation(null);
    }

}
