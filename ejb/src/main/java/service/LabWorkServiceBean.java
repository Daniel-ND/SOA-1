package service;

import dto.FilterValuesDTO;
import dto.LabWorkDTO;
import dto.ReceivedLabWorkDTO;
import entity.LabWork;
import mapper.LabWorkMapper;
import repository.CoordinatesRepository;
import repository.LabWorkRepository;
import repository.PersonRepository;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LabWorkServiceBean implements LabWorkService {
    private final LabWorkRepository repository;
    private final PersonRepository personRepository;
    private final CoordinatesRepository coordinatesRepository;

    private static LabWorkServiceBean INSTANCE;
    private LabWorkServiceBean() {
        repository = new LabWorkRepository();
        personRepository = new PersonRepository();
        coordinatesRepository = new CoordinatesRepository();
    }

    public static synchronized LabWorkServiceBean getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LabWorkServiceBean();
        }

        return INSTANCE;
    }

    @Override
    public void delete(Integer id) {
        LabWork labWork = repository.findById(id);
        repository.delete(labWork);
    }

    @Override
    public LabWorkDTO update(ReceivedLabWorkDTO labWorkDTO) {
        LabWork labWorkToSave = repository.findById(labWorkDTO.getId());
        labWorkToSave.setAuthor(personRepository.findById(labWorkDTO.getAuthor()));
        labWorkToSave.setCoordinates(coordinatesRepository.findById(labWorkDTO.getCoordinates()));
        labWorkToSave.setDifficulty(labWorkDTO.getDifficulty());
        labWorkToSave.setMinimalPoint(labWorkDTO.getMinimalPoint());
        labWorkToSave.setName(labWorkDTO.getName());
        repository.update(labWorkToSave);
        return LabWorkMapper.toDTO(labWorkToSave);
    }

    @Override
    public LabWorkDTO create(ReceivedLabWorkDTO labWorkDTO) {
        //validate
        LabWork labWork = new LabWork();
        labWork.setAuthor(personRepository.findById(labWorkDTO.getAuthor()));
        labWork.setCoordinates(coordinatesRepository.findById(labWorkDTO.getCoordinates()));
        labWork.setDifficulty(labWorkDTO.getDifficulty());
        labWork.setMinimalPoint(labWorkDTO.getMinimalPoint());
        labWork.setName(labWorkDTO.getName());
        repository.create(labWork);
        return LabWorkMapper.toDTO(labWork);
    }

    @Override
    public LabWorkDTO getById(Integer id) {
        return LabWorkMapper.toDTO(repository.findById(id));
    }

    @Override
    public List<LabWorkDTO> getAll(FilterValuesDTO filterValues) {
        repository.setFilters(filterValues);
        List<LabWork> labWorks = repository.findAll();
        List<LabWorkDTO> labWorkDTOList = new ArrayList<>();
        for (LabWork labWork: labWorks) {
            labWorkDTOList.add(LabWorkMapper.toDTO(labWork));

        }
        repository.disableFilters();
        return labWorkDTOList;
    }
}
