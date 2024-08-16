package lords.logic.green.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lords.logic.green.model.enums.TransportType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Double distance;

    @ManyToOne
    private Location startLocation;

    @ManyToOne
    private Location endLocation;

    @ManyToOne
    private User user;

    @ManyToOne
    private Transport transport;

    @CreationTimestamp
    private Instant createdDatetime;

}
