package hu.pannonuni.routerangers.entity.cargo;

import hu.pannonuni.routerangers.entity.base.Identifier;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "box")
public class Box extends Identifier {

    private Integer width;
    private Integer length;
    private Integer height;
    private Integer weight;
}
