package mapper;

import dto.CoordinatesDTO;
import entity.Coordinates;

public class CoordinatesMapper {
    public static CoordinatesDTO toDTO(Coordinates coordinates) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setId(coordinates.getId());
        coordinatesDTO.setX(coordinates.getX());
        coordinatesDTO.setY(coordinates.getY());
        return coordinatesDTO;
    }

    public static Coordinates toEntity(CoordinatesDTO coordinatesDTO) {
        Coordinates coordinates = new Coordinates();
        coordinates.setId(coordinatesDTO.getId());
        coordinates.setX(coordinatesDTO.getX());
        coordinates.setY(coordinatesDTO.getY());
        return coordinates;
    }
}
