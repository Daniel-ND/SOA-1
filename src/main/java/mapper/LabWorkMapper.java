package mapper;

import dto.LabWorkDTO;
import entity.LabWork;

public class LabWorkMapper {
    public static LabWorkDTO toDTO(LabWork labWork) {
        LabWorkDTO labWorkDTO = new LabWorkDTO();
        labWorkDTO.setId(labWork.getId());
        labWorkDTO.setDifficulty(labWork.getDifficulty());
        labWorkDTO.setCreationDate(labWork.getCreationDate());
        labWorkDTO.setCoordinates(CoordinatesMapper.toDTO(labWork.getCoordinates()));
        labWorkDTO.setName(labWork.getName());
        labWorkDTO.setAuthor(PersonMapper.toDTO(labWork.getAuthor()));
        labWorkDTO.setMinimalPoint(labWork.getMinimalPoint());
        return labWorkDTO;
    }
    public static LabWork toEntity(LabWorkDTO labWorkDTO) {
        LabWork labWork = new LabWork();
        labWork.setId(labWorkDTO.getId());
        labWork.setDifficulty(labWorkDTO.getDifficulty());
        labWork.setCreationDate(labWorkDTO.getCreationDate());
        labWork.setCoordinates(CoordinatesMapper.toEntity(labWorkDTO.getCoordinates()));
        labWork.setName(labWorkDTO.getName());
        labWork.setAuthor(PersonMapper.toEntity(labWorkDTO.getAuthor()));
        labWork.setMinimalPoint(labWorkDTO.getMinimalPoint());
        return labWork;
    }
}
