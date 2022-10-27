package com.zerobase.weather.controller;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @ApiOperation(value="다이어리 생성", notes="날짜와 텍스트 내용을 받아 다이어리를 생성하는 API 입니다.")
    @PostMapping("/create/diary")
    public String createDiary(
            @ApiParam(value="날짜 형식: yyyy-MM-dd", example="다이어리를 생성할 날짜입니다.(ex. 2022-10-27)")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,

            @RequestBody String text
    ) {
        log.trace("Creating Diary at " + date + ", Text: " + text.substring(0, Math.min(text.length(), 10)) + "...");
        diaryService.createDiary(date, text);
        return "complete";
    }

    @ApiOperation(value="특정 날짜의 다이어리 조회", notes="특정 날짜를 받아 해당 날짜의 다이어리 데이터를 모두 가져오는 API입니다.")
    @GetMapping("/read/diary")
    public List<Diary> readDiary(
            @ApiParam(value="날짜 형식: yyyy-MM-dd", example="다이어리를 조회할 날짜입니다.(ex. 2022-10-27)")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        log.trace("Read All Diaries at " + date);
        return diaryService.readDiary(date);
    }

    @ApiOperation(value="시작일부터 종료일까지 다이어리 조회", notes="시작일과 종료일의 날짜를 받아 날짜 사이의 다이어리 데이터를 모두 가져오는 API입니다.")
    @GetMapping("/read/diaries")
    public List<Diary> readDiaries(
            @ApiParam(value="날짜 형식: yyyy-MM-dd", example="다이어리를 조회할 시작일 날짜입니다.(ex. 2022-10-27)")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @ApiParam(value="날짜 형식: yyyy-MM-dd", example="다이어리를 조회할 종료일 날짜입니다.(ex. 2022-10-27)")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate
    ) {
        log.trace("Read All Diaries from " + startDate + " to " + endDate);
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation(value="특정 날짜의 첫번째 다이어리 내용 수정", notes="특정 날짜와 텍스트 내용을 받아 해당 날짜에 해당하는 첫번째 다이어리의 내용을 업데이트 하는 API입니다.")
    @PutMapping("/update/diary")
    public void updateDiary(
            @ApiParam(value="날짜 형식: yyyy-MM-dd", example="다이어리를 수정하기 위한 날짜입니다.(ex. 2022-10-27)")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,

            @ApiParam(value="다이어리 내용", example="다이어리를 수정할 텍스트 형식의 내용입니다.")
            @RequestBody
            String text
    ) {
        log.info("Update First Diary at " + date + " to Text: " + text.substring(0, Math.min(text.length(), 10)) + "...");
        diaryService.updateDiary(date, text);
    }

    @ApiOperation(value="특정 날짜의 다이어리를 모두 삭제", notes="특정 날짜를 받아 해당 날짜의 다이어리 데이터리를 모두 삭제하는 API입니다.")
    @DeleteMapping("/delete/diary")
    public void deleteDiary(
            @ApiParam(value="날짜 형식: yyyy-MM-dd", example="다이어리를 삭제할 날짜입니다.(ex. 2022-10-27)")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        log.info("Delete All Diaries at " + date);
        diaryService.deleteDiaries(date);
    }
}
