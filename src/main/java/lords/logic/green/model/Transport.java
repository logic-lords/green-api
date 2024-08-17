package lords.logic.green.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lords.logic.green.model.enums.FuelType;
import lords.logic.green.model.enums.TransportType;
import lords.logic.green.model.enums.TransportTypeSize;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private TransportType type;
    @Enumerated(EnumType.STRING)
    private TransportTypeSize size;
    private Integer placesNumber;
    private Double co2Emission;
    private Double fuelConsumptionPerKm;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @CreationTimestamp
    private Instant createdDatetime;
}

