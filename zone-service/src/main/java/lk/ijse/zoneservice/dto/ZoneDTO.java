package lk.ijse.zoneservice.dto;

import lombok.Data;

@Data
public class ZoneDTO {

    private String name;
    private double minTemp;
    private double maxTemp;
}