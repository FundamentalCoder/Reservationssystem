package ch.m223.reservationssystem.Repository;

import ch.m223.reservationssystem.Model.Reservation;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByPrivateCode(String privateCode);

    Optional<Reservation> findByPublicCode(String publicCode);

    List<Reservation> findByDatumAndZimmer(LocalDate datum, Integer zimmer);
}
