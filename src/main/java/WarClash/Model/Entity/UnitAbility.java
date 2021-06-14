package WarClash.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class UnitAbility extends IdentifiableEntity{

    @ManyToOne
    private Ability ability;

    @ManyToOne
    private Unit unit;

    private Integer descriptor;

    private Integer value;
}
