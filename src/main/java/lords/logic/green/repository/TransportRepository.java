package lords.logic.green.repository;

import lords.logic.green.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, String> {
}
