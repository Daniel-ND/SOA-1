package dto;

import entity.Difficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class LabWorkDTO {
    private int id;
    private String name;
    private CoordinatesDTO coordinates;
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private ZonedDateTime creationDate;
    private Double minimalPoint;
    private Difficulty difficulty;
    private PersonDTO author;
}
