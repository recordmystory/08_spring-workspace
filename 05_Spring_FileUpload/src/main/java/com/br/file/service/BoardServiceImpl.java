package com.br.file.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.file.dao.BoardDao;
import com.br.file.dto.AttachDto;
import com.br.file.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDao boardDao;

	@Override
	public int insertOneFileBoard(BoardDto board, AttachDto attach) {
		int result1 = boardDao.insertBoard(board);
		int result2 = 1;

		if (attach != null) { // 첨부파일이 있을 경우
			result2 = boardDao.insertAttach(attach);
		}

			return result1 * result2;
	}

	@Override
	public int insertManyFileBoard(BoardDto board, List<AttachDto> list) {
		int result1 = boardDao.insertBoard(board);
		
		int result2 = 1;
		
		if(!list.isEmpty()) { // 첨부파일이 있었을 경우
			result2 = 0;
			for(AttachDto attach : list) { 
				result2 += boardDao.insertAttach(attach);
			}
		}
		
		return result1 * result2;
	}

	@Override
	public List<AttachDto> selectAtList() {
		return boardDao.selectAtList();
	}

}
