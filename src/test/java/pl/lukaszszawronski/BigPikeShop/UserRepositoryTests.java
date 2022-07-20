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
    public void testCreateNewUserWithOneRole(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userLukaS = new User("lukasz.szawronski@gmail.com",
                "luka2022",
                "Lukasz",
                "Szawronski");
        userLukaS.addRole(roleAdmin);

        User savedUser = repo.save(userLukaS);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testCreateNewUserWithTwoRoles() {
        User userLuka1 = new User("szawercq@interia.pl",
                "luka12022",
                "Luka",
                "Szawr");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);
        userLuka1.addRole(roleEditor);
        userLuka1.addRole(roleAssistant);

        User savedUser = repo.save(userLuka1);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user));
    }
    @Test
    public void testGetUserByID(){
        User userLukaS = repo.findById(1).get();
        System.out.println(userLukaS);
        assertThat(userLukaS).isNotNull();
    }
    @Test
    public void testUpdateUserDetails(){
        User userLukaS = repo.findById(1).get();
        userLukaS.setEnabled(true);
        userLukaS.setEmail("szawero@gmail.com");

        repo.save(userLukaS);
    }
    @Test
    public void testUpdateUserRoles(){
        User userLuka1 = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);
        userLuka1.getRoles().remove(roleEditor);
        userLuka1.addRole(roleSalesperson);
        repo.save(userLuka1);
    }
    @Test
    public void testDeleteUser(){
        Integer userId = 2;
        repo.deleteById(userId);
    }

    @Test 
    public void testGetUserByEmail(){
        String email = "szawero@gmail.com";
        User user = repo.getUserByEmail(email);
        assertThat(user).isNotNull();
    }
    @Test
    public void testCountByID(){
        Integer id = 1;
        Long countById = repo.countById(id);

        assertThat(countById).isNotNull().isGreaterThan(0);
    }
}
