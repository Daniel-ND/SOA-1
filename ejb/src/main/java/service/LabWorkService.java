package service;

import dto.FilterValuesDTO;
import dto.LabWorkDTO;
import dto.ReceivedLabWorkDTO;

import java.util.List;

public interface LabWorkService {
    void delete(Integer id);
    LabWorkDTO update(ReceivedLabWorkDTO labWorkDTO);
    LabWorkDTO create(ReceivedLabWorkDTO labWorkDTO);
    LabWorkDTO getById(Integer id);
    List<LabWorkDTO> getAll(FilterValuesDTO filterValues);


}
