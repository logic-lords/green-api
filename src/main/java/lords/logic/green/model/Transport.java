package lords.logic.green.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lords.logic.green.model.enums.TransportType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private TransportType type;
    private String placesNumber;
    private String co2Emission;
    private String fuelConsumptionPerKm;
    @CreationTimestamp
    private Instant createdDatetime;
}
