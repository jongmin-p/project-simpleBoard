package com.jmpark.simpleBoard.service;

import com.jmpark.simpleBoard.dao.BoardDao;
import com.jmpark.simpleBoard.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;


    @Override
    public int write(BoardDto boardDto) throws Exception {
        // throw new Exception("test");         // <-   게시글 작성 실패 테스트용
        return boardDao.insert(boardDto);
    }

    @Override
    public BoardDto read(Integer boardNo) throws Exception {
        BoardDto boardDto = boardDao.select(boardNo);
        boardDao.increaseViewCnt(boardNo);

        return boardDto;
    }

    @Override
    public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public int modify(BoardDto boardDto) throws Exception {
        return boardDao.update(boardDto);
    }

    @Override
    public int remove(Integer boardNo, String writer) throws Exception {
        return boardDao.delete(boardNo, writer);
    }

    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }

    // 페이징 처리 메서드
    @Override
    public List<BoardDto> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }
}