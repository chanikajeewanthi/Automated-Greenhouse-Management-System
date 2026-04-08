package lk.ijse.zoneservice.service;

import lk.ijse.zoneservice.client.IoTClient;
import lk.ijse.zoneservice.dto.DeviceResponseDTO;
import lk.ijse.zoneservice.dto.ZoneDTO;
import lk.ijse.zoneservice.dto.ZoneResponseDTO;
import lk.ijse.zoneservice.entity.Zone;
import lk.ijse.zoneservice.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository repo;
    private final IoTClient iotClient;

    // CREATE
    public ZoneResponseDTO create(ZoneDTO dto, String token) {

        if (dto.getMinTemp() >= dto.getMaxTemp()) {
            throw new RuntimeException("minTemp must be less than maxTemp");
        }

        Zone zone = new Zone();
        zone.setName(dto.getName());
        zone.setMinTemp(dto.getMinTemp());
        zone.setMaxTemp(dto.getMaxTemp());

        DeviceResponseDTO device = iotClient.registerDevice(
                dto.getName(),
                "Zone-" + dto.getName(),
                token
        );

        zone.setDeviceId(device.getDeviceId());

        Zone saved = repo.save(zone);

        return mapToResponse(saved);
    }

    // GET BY ID
    public ZoneResponseDTO getById(Long id) {
        Zone zone = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        return mapToResponse(zone);
    }

    // GET ALL
    public List<ZoneResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ZoneResponseDTO update(Long id, ZoneDTO dto) {

        Zone zone = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        if (dto.getMinTemp() >= dto.getMaxTemp()) {
            throw new RuntimeException("Invalid temperature range");
        }

        zone.setName(dto.getName());
        zone.setMinTemp(dto.getMinTemp());
        zone.setMaxTemp(dto.getMaxTemp());

        return mapToResponse(repo.save(zone));
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private ZoneResponseDTO mapToResponse(Zone zone) {
        ZoneResponseDTO dto = new ZoneResponseDTO();
        dto.setId(zone.getId());
        dto.setName(zone.getName());
        dto.setMinTemp(zone.getMinTemp());
        dto.setMaxTemp(zone.getMaxTemp());
        dto.setDeviceId(zone.getDeviceId());
        return dto;
    }
}