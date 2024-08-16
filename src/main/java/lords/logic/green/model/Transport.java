package lords.logic.green.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lords.logic.green.model.enums.TransportType;
import lords.logic.green.model.enums.TransportTypeSize;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@Entity
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private TransportType type;
    @Enumerated(EnumType.STRING)
    private TransportTypeSize size;
    private String placesNumber;
    private Double co2Emission;
    private Double fuelConsumptionPerKm;
    @CreationTimestamp
    private Instant createdDatetime;
}

