package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.SnackNietGevondenException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcSnackRepository implements SnackRepository{
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final RowMapper<Snack> snackMapper =
            (result, rowNum) -> new Snack(result.getLong("id"),
                    result.getString("naam"),
                    result.getBigDecimal("prijs"));
    private final RowMapper<BigDecimal> prijsMapper =
            (result, rowNum) -> result.getBigDecimal("prijs");

    public JdbcSnackRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                .withTableName("snacks").usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<Snack> findById(long id) {
        try {
            var sql = "select id, naam, prijs from snacks where id = ?";
            return Optional.of(template.queryForObject(sql, snackMapper, id));
        }catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public void update(Snack snack) {
        var sql = "update snacks set naam=?, prijs=? where id = ?";
        if (template.update(sql, snack.getNaam(), snack.getPrijs(),
                snack.getId()) == 0){
            throw new SnackNietGevondenException();
        }
    }

    @Override
    public List<Snack> findByBeginName(String beginNaam) {
        var sql = "select id, naam, prijs from snacks where lower(naam) like concat(?, '%')";
        return template.query(sql, snackMapper, beginNaam);
    }
}
