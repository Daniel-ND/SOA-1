package dto;

import entity.Difficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class ReceivedLabWorkDTO {
    private int id;
    private String name;
    private Integer coordinates;
    private Double minimalPoint;
    private Difficulty difficulty;
    private Integer author;
}
