package lk.ijse.zoneservice.dto;

import lombok.Data;

@Data
public class ZoneResponseDTO {
    private Long id;
    private String name;
    private double minTemp;
    private double maxTemp;
    private String deviceId;
}