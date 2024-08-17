package lords.logic.green.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lords.logic.green.model.enums.TransportType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "\"trip\"")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Double distance;

    private Integer onboard;

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
