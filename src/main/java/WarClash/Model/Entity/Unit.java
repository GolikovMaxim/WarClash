package WarClash.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Unit extends IdentifiableEntity{

    public enum AttackType {
        MELEE, RANGED, MAGIC
    }

    public enum ArmorType {
        HEAVY, LIGHT, ENCHANTED
    }

    private String nameKey;

    @Enumerated(EnumType.ORDINAL)
    private AttackType attackType;

    private Integer attack;

    private Integer attackSpeed;

    private Integer armor;

    @Enumerated(EnumType.ORDINAL)
    private ArmorType armorType;

    private Integer moveSpeed;

    private Integer hp;

    private Integer hpRegen;

    private Integer mp;

    private Integer mpRegen;

    private Float unitSize;

    @ManyToOne
    private Race race;

    @OneToMany(mappedBy = "unit")
    private Set<UnitAbility> unitAbilities;
}