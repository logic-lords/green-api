package lords.logic.green.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lords.logic.green.rest.dto.TripDto;
import lords.logic.green.rest.dto.TripEmissionDto;
import lords.logic.green.rest.dto.TransportDto;
import lords.logic.green.rest.mapper.TripEmissionMapper;
import lords.logic.green.rest.mapper.TripMapper;
import lords.logic.green.rest.mapper.TransportMapper;
import lords.logic.green.service.ComputeService;
import lords.logic.green.service.TripService;
import lords.logic.green.service.TransportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Transport")
public class TransportController {
    private final TransportService service;
    private final TransportMapper mapper;
    private final TransportService transportService;

    @GetMapping("/transports")
    public List<TransportDto> getTransports(){
        return service.getTransports().stream().map(mapper::toTransportDto).toList();
    }

    @GetMapping("/transports/{transportId}")
    public TransportDto getTransport(@PathVariable String transportId){
        return mapper.toTransportDto(service.getTransport(transportId));
    }

    @PutMapping("/transports")
    public List<TransportDto> cruptdateTransport(@RequestBody List<TransportDto> transportDtos){

        return transportService.crupdateTransport(
                transportDtos.stream().map(mapper::toDomain).toList()
        ).stream().map(mapper::toTransportDto).toList();
    }
}
