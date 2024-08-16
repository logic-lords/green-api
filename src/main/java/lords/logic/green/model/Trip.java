package lords.logic.green.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name ="start_location_id")
    private Location startLocation;

    @ManyToOne
    @JoinColumn(name ="end_location_id")
    private Location endLocation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @CreationTimestamp
    private Instant createdDatetime;

}
