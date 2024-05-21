package com.jmpark.simpleBoard.service;

import com.jmpark.simpleBoard.dao.BoardDao;
import com.jmpark.simpleBoard.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    // 깃에서 다 복붙한거임.
    // 여기선 딱히 Transaction 처리해줄 것이 없기 때문에, try-catch 없이 그냥 컨트롤러로 에러를 던짐.

    @Autowired
    BoardDao boardDao;          // BoardDao 주입 받기 (실제로는 BoardDaoImpl)


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

    // 페이징 처리 메서드
    @Override
    public List<BoardDto> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }

    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }
}