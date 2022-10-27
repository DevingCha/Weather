package com.zerobase.weather.repository;

import com.zerobase.weather.domain.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcMemoRepositoryTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest() {
        // given
        int memoId = 1;
        Memo memo = Memo.builder().id(memoId).text("this is new memo").build();

        // when
        jdbcMemoRepository.save(memo);

        // then
        Optional<Memo> result = jdbcMemoRepository.findById(memoId);
        assertEquals(memo.getId(), result.get().getId());
        assertEquals(memo.getText(), result.get().getText());
    }

    @Test
    void findAllMemoTest() {
        List<Memo> memoList = jdbcMemoRepository.findAll();
        assertNotNull(memoList);
    }

}