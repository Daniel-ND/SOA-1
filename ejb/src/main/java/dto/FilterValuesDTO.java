package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

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
