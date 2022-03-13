package mapper;

import dto.LocationDTO;
import entity.Location;

public class LocationMapper {
    public static LocationDTO toDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setX(location.getX());
        locationDTO.setY(location.getY());
        return locationDTO;
    }

    public static Location toEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setId(locationDTO.getId());
        location.setName(locationDTO.getName());
        location.setX(locationDTO.getX());
        location.setY(locationDTO.getY());
        return location;
    }
}
