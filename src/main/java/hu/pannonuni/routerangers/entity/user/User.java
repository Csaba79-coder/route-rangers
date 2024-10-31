package hu.pannonuni.routerangers.entity.user;

import hu.pannonuni.routerangers.entity.base.Identifier;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "route_rangers_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Identifier {

    private String email;

    private String password;
}
