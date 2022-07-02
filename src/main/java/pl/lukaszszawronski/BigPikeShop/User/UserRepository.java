package pl.lukaszszawronski.BigPikeShop.User;

import org.springframework.data.repository.CrudRepository;
import pl.lukaszszawronski.BigPikeShop.Entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
