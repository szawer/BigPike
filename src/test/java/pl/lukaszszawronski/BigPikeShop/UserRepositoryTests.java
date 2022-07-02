package pl.lukaszszawronski.BigPikeShop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import pl.lukaszszawronski.BigPikeShop.Entity.Role;
import pl.lukaszszawronski.BigPikeShop.Entity.User;
import pl.lukaszszawronski.BigPikeShop.User.UserRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userLukaS = new User("lukasz.szawronski@gmail.com",
                "luka2022",
                "Lukasz",
                "Szawronski");
        userLukaS.addRole(roleAdmin);

        User savedUser = repo.save(userLukaS);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
