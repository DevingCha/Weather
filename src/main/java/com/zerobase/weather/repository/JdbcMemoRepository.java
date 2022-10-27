package com.zerobase.weather.repository;

import com.zerobase.weather.domain.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMemoRepository {
    private final JdbcTemplate jdbcTemplate;

    public Memo save(Memo memo) {
        String sql = "insert into memo values (?, ?)";
        jdbcTemplate.update(sql, memo.getId(), memo.getText());
        return memo;
    }

    public List<Memo> findAll() {
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper());
    }

    public Optional<Memo> findById(int id) {
        String sql = "select * from memo where id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
    }

    /**
     * JDBC 를 통해서 가져온 데이터가
     * ResultSet 의 데이터 형태를 띤다
     * 이 ResultSet을 Memo형식으로 맵핑해주는 것을 만드는 과정이다.
     */
    private RowMapper<Memo> memoRowMapper() {
        return (rs, rowNum) -> Memo.builder()
                .id(rs.getInt("id"))
                .text(rs.getString("text"))
                .build();
    }
}
