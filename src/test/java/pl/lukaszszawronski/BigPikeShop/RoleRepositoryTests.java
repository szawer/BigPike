package pl.lukaszszawronski.BigPikeShop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.lukaszszawronski.BigPikeShop.Entity.Role;
import pl.lukaszszawronski.BigPikeShop.User.RoleRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateFirstRole(){
        Role roleAdmin = new Role("Admin", "manage everything");
        Role savedRole = repo.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }
    @Test
    public void testCreateRoles(){
        Role roleSalesperson = new Role("Salesperson", "manage product price "
                + "customers, shipping, orders and sales reports ");
        Role roleEditor = new Role("Editor", "manage categories, brands "
        + "products, sales reports, articles, menus");
        Role roleShipper = new Role("Shipper", "view products, orders, " +
                "update order status");
        Role roleAssistant = new Role("Assistant", "manage questions and reviews");
        repo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
    }
}
