package com.jmpark.simpleBoard.service;

import com.jmpark.simpleBoard.dao.BoardDao;
import com.jmpark.simpleBoard.dao.CommentDao;
import com.jmpark.simpleBoard.domain.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    // 댓글이 추가되거나 삭제되면,  boardDao 에도 영향이 간다.
    // board 테이블에 commentCnt 컬럼이 있기 때문
    BoardDao boardDao;
    CommentDao commentDao;

    // 생성자로 주입 (변수 즉, 필드로 주입하는 것보다, 생성자 주입으로 쓰라는 이유는 인스턴스 변수에 하나하나 @Autowired 쓰면 빼먹거나 실수하기 쉬움)
    // @Autowired
    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
        this.commentDao = commentDao;
        this.boardDao = boardDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDto commentDto) throws Exception {
        // 1. 댓글 생성 시, boardDao 에 있는 commentCnt 를 1 올려줌.
        boardDao.updateCommentCnt(commentDto.getBoardNo(), 1);

        // throw new Exception("test");

        // 2. 그 다음에, commentDao 에다가 INSERT
        return commentDao.insert(commentDto);

        // 위의 2가지 작업이 모두 성공해야 하기에,  @Transactional 을 걸었다.   (예외 발생 시 rollback 하도록 설정해 둠)
    }

    @Override
    public CommentDto read(Integer cno) throws Exception {
        return commentDao.select(cno);
    }

    @Override
    public List<CommentDto> getList(Integer bno) throws Exception {
        // throw new Exception("test");

        return commentDao.selectAll(bno);
    }

    @Override
    public int modify(CommentDto commentDto) throws Exception {
        return commentDao.update(commentDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) throws Exception {
        // 1. 댓글 삭제 시, 먼저 updateCommentCnt 를 -1 시키고,
        int rowCnt = boardDao.updateCommentCnt(bno, -1);
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);

        // throw new Exception("test");           // <- 주석 처리한 이유는 일부러 예외 발생시켜서 rollback 하는지 체크했음

        // 2. 그 다음에, 댓글 지우기.
        rowCnt = commentDao.delete(cno, commenter);
        System.out.println("rowCnt = " + rowCnt);

        // 위의 2가지 작업이 모두 성공해야 하기에,  @Transactional 을 걸었다.   (예외 발생 시 rollback 하도록 설정해 둠)

        return rowCnt;
    }

    @Override
    public int getCount(Integer bno) throws Exception {
        return commentDao.count(bno);
    }
}