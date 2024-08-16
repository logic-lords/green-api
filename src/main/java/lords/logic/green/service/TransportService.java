package lords.logic.green.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lords.logic.green.model.Transport;
import lords.logic.green.model.exception.NotFoundException;
import lords.logic.green.repository.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransportService {
    private final TransportRepository repository;
    public Transport getTransport(String transportId){
        return repository.findById(transportId).orElseThrow(() -> new NotFoundException(
                "Transport" +
                " " +
                "of" +
                " id: " + transportId + " not found."));
    }

    public List<Transport> getTransports(){
        return repository.findAll();
    }

    @Transactional
    public List<Transport> crupdateTransport(List<Transport> transports){
        return repository.saveAll(transports);
    }
}
