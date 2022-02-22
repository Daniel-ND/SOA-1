package dto;

import entity.Difficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class FilterValuesDTO {
    private String id;
    private String name;
    private String coordinate_id;
    private String minimalPoint;
    private String difficulty;
    private String person_id;
}
