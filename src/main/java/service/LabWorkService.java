package service;

import dto.FilterValuesDTO;
import dto.LabWorkDTO;
import dto.ReceivedLabWorkDTO;
import entity.LabWork;
import mapper.LabWorkMapper;
import repository.CoordinatesRepository;
import repository.LabWorkRepository;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

public class LabWorkService {
    private final LabWorkRepository repository;
    private final PersonRepository personRepository;
    private final CoordinatesRepository coordinatesRepository;

    private static LabWorkService INSTANCE;
    private LabWorkService() {
        repository = new LabWorkRepository();
        personRepository = new PersonRepository();
        coordinatesRepository = new CoordinatesRepository();
    }

    public static synchronized LabWorkService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LabWorkService();
        }

        return INSTANCE;
    }

    public void delete(Integer id) {
        LabWork labWork = repository.findById(id);
        repository.delete(labWork);
    }

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

    public LabWorkDTO getById(Integer id) {
        return LabWorkMapper.toDTO(repository.findById(id));
    }

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

    public Long countMinimalPointGreaterThan(Double minimalPoint) {
        return repository.countMinimalPointGreaterThan(minimalPoint);
    }

    public LabWorkDTO deleteSingleByAuthor(String author) {
        List<LabWork> labWorks = repository.findAll();
        LabWork labWorkToDelete = null;
        for (LabWork labWork: labWorks) {
            System.out.println(labWork.getName());
            if (labWork.getAuthor().getName().equals(author)) {
                labWorkToDelete = labWork;
                System.out.println("kek");
                break;
            }
        }
        if (labWorkToDelete == null)
            return null;
        System.out.println("here");
        repository.delete(labWorkToDelete);
        System.out.println(labWorkToDelete);
        return LabWorkMapper.toDTO(labWorkToDelete);
    }
}
