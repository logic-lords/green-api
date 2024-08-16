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
import lords.logic.green.model.enums.LocationType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String name;
    @Enumerated(EnumType.STRING)
    private LocationType locationType;
    @CreationTimestamp
    private Instant createdDatetime;
}
