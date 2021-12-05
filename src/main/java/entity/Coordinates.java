package entity;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = {"x", "y"})
@Table(name = "coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "x should not be null")
    @DecimalMin(value = "-70", inclusive = false, message = "x must be > -70")
    private Integer x; //Значение поля должно быть больше -70, Поле не может быть null

    @NotNull(message = "x should not be null")
    @DecimalMax(value = "132", inclusive = true, message = "y must be <= 132")
    private Double y; //Максимальное значение поля: 132, Поле не может быть null
}