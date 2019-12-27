package org.zerock.board.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.zerock.board.domain.TBoard;
import org.zerock.board.domain.TReply;
import org.zerock.board.dto.SearchDTO;

import org.zerock.board.repo.tboard.TBoardRepositoryCustom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.zerock.board.dto.SearchTypeEnum.TITLE;


@SpringBootTest
@Slf4j
public class TBoardRepoTests {

    @Autowired
    private TBoardRepository tBoardRepository;

    @Autowired
    private TReplyRepository tReplyRepository;




    @Test
    public void add100() {

        IntStream.range(100,201).forEach(i -> {

            TBoard board = TBoard.builder().title("Title...." + i).content("Content..........." + i ).build();

            //log.info(""+ tBoardRepository.save(board));

        });

    }

    @Test
    public void addReplyRandom() {

        IntStream.range(1,101).forEach(i -> {

            int bno = (int)(Math.random() * 100) + 1;

            TBoard board = TBoard.builder().bno(bno).build();

            TReply reply = TReply.builder().board(board).replyText("reply...." + i).build();

            log.info("RESULT: "+tReplyRepository.save(reply));


        });

    }

    @Test
    public void test1() {
        log.info("" + tBoardRepository.getClass().getName());
        log.info("------------------------------------------");


        SearchDTO searchDTO = new SearchDTO(TITLE, "50");


        List<Object[]> result = tBoardRepository.getCustomList(searchDTO,PageRequest.of(0,10, Sort.Direction.DESC ,"bno"));

        result.forEach(arr -> {
            log.info(Arrays.toString(arr));
        });



    }
}
