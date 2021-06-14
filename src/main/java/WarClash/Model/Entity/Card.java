package WarClash.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Card extends IdentifiableEntity{

    public enum Rarity {
        COMMON, RARE, EPIC, LEGENDARY
    }

    @Enumerated(EnumType.ORDINAL)
    private Rarity rarity;

    private String descriptionKey;

    private Integer tier;

    @OneToOne
    private Unit unit;

    @OneToMany(mappedBy = "card")
    private Set<WarClashUserCard> warClashUserCards;
}
