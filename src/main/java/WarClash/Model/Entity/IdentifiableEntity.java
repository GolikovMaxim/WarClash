package WarClash.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class IdentifiableEntity {

    @Id
    @GeneratedValue
    private Integer id;
}
