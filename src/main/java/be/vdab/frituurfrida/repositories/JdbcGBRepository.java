package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.GastenboekBericht;
import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.BerichtNietGevondenException;
import be.vdab.frituurfrida.exceptions.GastenboekLeegException;
import be.vdab.frituurfrida.exceptions.SnackNietGevondenException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcGBRepository implements GastenBoekRepository{
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final RowMapper<GastenboekBericht> berichtMapper =
            (result, rowNum) ->
        new GastenboekBericht(result.getLong("idgastenboek"),
                    result.getString("naam"),
                    result.getString("bericht"),
                    result.getDate("datum").toLocalDate());

    public JdbcGBRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                .withTableName("gastenboek").usingGeneratedKeyColumns("idgastenboek");
    }

    @Override
    public Optional<GastenboekBericht> findById(long id) {
        try {
            var sql = "select idgastenboek, naam, bericht, datum from gastenboek where idgastenboek = ?";
            return Optional.of(template.queryForObject(sql, berichtMapper, id));
        }catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<GastenboekBericht> findAll() throws GastenboekLeegException{
        var sql = "select idgastenboek, naam, bericht, datum from gastenboek";
        if (template.query(sql, berichtMapper).isEmpty()){
            throw new GastenboekLeegException();
        }
        return template.query(sql, berichtMapper);
    }

    @Override
    public void update(GastenboekBericht gastenboekBericht) {
        var sql = "update gastenboek set naam=?, bericht=?, datum=? where idgastenboek = ?";
        if (template.update(sql, gastenboekBericht.getNaam(), gastenboekBericht.getBericht(),
                gastenboekBericht.getDatum(), gastenboekBericht.getId()) == 0){
            throw new BerichtNietGevondenException();
        }
    }

    @Override
    public void delete(GastenboekBericht gastenboekBericht) {
        var sql = "delete from gastenboek where idgastenboek = ?";
        if (template.update(sql, gastenboekBericht.getId()) == 0){
            throw new BerichtNietGevondenException();
        }
    }

    @Override
    public long insert(GastenboekBericht gastenboekBericht) {
        var kolomwaarden =
                Map.of("naam", gastenboekBericht.getNaam(),
                "bericht", gastenboekBericht.getBericht(),
                "datum", gastenboekBericht.getDatum());
        var id = insert.executeAndReturnKey(kolomwaarden);
        return id.longValue();
    }

}
