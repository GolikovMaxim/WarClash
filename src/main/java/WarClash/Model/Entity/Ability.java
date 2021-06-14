package WarClash.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Ability extends IdentifiableEntity{

    public enum TargetType {
        ANY_TARGET, ENEMY, ALLY, NO_TARGET, SELF
    }

    private String nameKey;

    private String descriptionKey;

    private Boolean aoe;

    @Enumerated(EnumType.ORDINAL)
    private TargetType targetType;

    @OneToMany
    private Set<UnitAbility> unitAbilities;
}
