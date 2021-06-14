package WarClash.Model.Entity;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Building extends IdentifiableEntity{

    private Integer cost;

    @OneToOne
    private Unit unit;

    @ManyToOne
    @Nullable
    private Building base;

    @OneToMany(mappedBy = "base")
    private Set<Building> upgrades;
}
