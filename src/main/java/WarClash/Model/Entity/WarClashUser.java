package WarClash.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class WarClashUser extends IdentifiableEntity{

    private String name;

    private String accessToken;

    private String refreshToken;

    private Integer experience;

    private Integer level;

    @OneToMany(mappedBy = "warClashUser")
    private Set<WarClashUserCard> warClashUserCard;
}
