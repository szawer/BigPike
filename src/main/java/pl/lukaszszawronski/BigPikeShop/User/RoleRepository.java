package pl.lukaszszawronski.BigPikeShop.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukaszszawronski.BigPikeShop.Entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
