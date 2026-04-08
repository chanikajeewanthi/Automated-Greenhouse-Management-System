package lk.ijse.zoneservice.controller;

import lk.ijse.zoneservice.dto.ZoneDTO;
import lk.ijse.zoneservice.dto.ZoneResponseDTO;
import lk.ijse.zoneservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService service;

    // CREATE
    @PostMapping
    public ZoneResponseDTO create(
            @RequestBody ZoneDTO dto,
            @RequestHeader("Authorization") String token
    ) {
        return service.create(dto, token.replace("Bearer ", ""));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ZoneResponseDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET ALL
    @GetMapping
    public List<ZoneResponseDTO> getAll() {
        return service.getAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ZoneResponseDTO update(@PathVariable Long id,
                                  @RequestBody ZoneDTO dto) {
        return service.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}