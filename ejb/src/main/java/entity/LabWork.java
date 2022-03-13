package entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@FilterDef(name = "idFilter",
        parameters = @ParamDef(name = "id", type = "integer"),
        defaultCondition = "id = :id")
@FilterDef(name = "nameFilter",
        parameters = @ParamDef(name = "name", type = "string"),
        defaultCondition = "name = :name")
@FilterDef(name = "coordinateIdFilter",
        parameters = @ParamDef(name = "coordinate_id", type = "integer"),
        defaultCondition = "coordinate_id = :coordinate_id")
@FilterDef(name = "minimalPointFilter",
        parameters = @ParamDef(name = "minimalPoint", type = "double"),
        defaultCondition = "minimalpoint = :minimalPoint")
@FilterDef(name = "difficultyFilter",
        parameters = @ParamDef(name = "difficulty", type = "string"),
        defaultCondition = "difficulty = :difficulty")
@FilterDef(name = "authorFilter",
        parameters = @ParamDef(name = "person_id", type = "integer"),
        defaultCondition = "person_id = :person_id")
@Table(name = "LabWork")
@NamedQuery(name = "Entity.Movie.getAll", query = "SELECT m FROM LabWork m")

@Filter(name = "idFilter")
@Filter(name = "nameFilter")
@Filter(name = "coordinateIdFilter")
@Filter(name = "minimalPointFilter")
@Filter(name = "difficultyFilter")
@Filter(name = "authorFilter")
public class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotNull(message = "name should not be null")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinate_id")
    private Coordinates coordinates; //Поле не может быть null


    @CreationTimestamp
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @NotNull(message = "x should not be null")
    @DecimalMin(value = "0", inclusive = false, message = "x must be > 0")
    private Double minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty; //Поле не может быть null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person author; //Поле не может быть null
}