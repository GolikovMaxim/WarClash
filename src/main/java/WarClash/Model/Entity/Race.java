package WarClash.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Race extends IdentifiableEntity{

    private String nameKey;

    private String descriptionKey;
}
