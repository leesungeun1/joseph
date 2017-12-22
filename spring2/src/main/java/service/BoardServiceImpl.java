package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDAOImpl;

@Service
public class BoardServiceImpl {

	@Autowired
	private BoardDAOImpl boardDao;
}
