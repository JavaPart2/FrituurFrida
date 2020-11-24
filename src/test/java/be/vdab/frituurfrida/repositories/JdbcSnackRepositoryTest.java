package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.SnackNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JdbcSnackRepository.class)
@Sql("/insertSnacks.sql")
public class JdbcSnackRepositoryTest
        extends AbstractTransactionalJUnit4SpringContextTests {
    private JdbcSnackRepository repository;

    public JdbcSnackRepositoryTest(JdbcSnackRepository repository) {
        this.repository = repository;
    }

    private long idVanTestSnack(){
        return super.jdbcTemplate.queryForObject(
                "select id from snacks where naam = 'test'", long.class);
    }

    @Test
    void findById(){
        assertThat(repository.findById(idVanTestSnack()).get().getNaam())
                .isEqualTo("test");
    }

    @Test
    void findByOnbestaandeId(){
        assertThat(repository.findById(-1)).isEmpty();
    }


    @Test
    void update(){
        var id = idVanTestSnack();
        var snack = new Snack(id, "test", BigDecimal.ONE);
        repository.update(snack);
        assertThat(super.jdbcTemplate.queryForObject(
                "select prijs from snacks where id=?", BigDecimal.class, id))
                .isOne();
    }

    @Test
    void updateOnbestaandeSnackFout(){
        assertThatExceptionOfType(SnackNietGevondenException.class)
                .isThrownBy(() -> repository.update(new Snack(-1, "test",
                        BigDecimal.ONE)));
    }

    @Test
    void findByBeginName(){
        String testBeginName = "Tes";
        assertThat(repository.findByBeginName(testBeginName))
                .allSatisfy(snack -> assertThat(snack.getNaam())
                        .startsWith(testBeginName));
    }
}
