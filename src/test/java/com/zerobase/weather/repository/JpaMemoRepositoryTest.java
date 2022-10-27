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
class JpaMemoRepositoryTest {
    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest() {
        // given
        Memo memo = Memo.builder().text("this is new memo").build();

        // when
        jpaMemoRepository.save(memo);

        // then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size() > 0);
    }

    @Test
    void findByIdTest() {
        //given
        Memo newMemo = Memo.builder().text("JPA").build();

        //when
        Memo saveMemo = jpaMemoRepository.save(newMemo);

        //then
        assertEquals(
            jpaMemoRepository.findById(saveMemo.getId()).get().getText()
            , newMemo.getText());
    }
}