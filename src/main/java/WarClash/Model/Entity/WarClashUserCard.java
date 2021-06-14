package WarClash.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class WarClashUserCard extends IdentifiableEntity{

    @ManyToOne
    private WarClashUser warClashUser;

    @ManyToOne
    private Card card;

    private Integer count;

    private Integer level;
}
