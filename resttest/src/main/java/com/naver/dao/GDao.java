package com.naver.dao;

import java.util.List;

import com.naver.model.ComBean;
import com.naver.model.GBean;

public interface GDao {

	List<GBean> getGList();//목록
	GBean getCont(int g_no);//방명록 내용
	void insertCom(ComBean c);//댓글 저장
	int getComCount(int g_no);//각 번호에 해당하는 댓글 개수
	void updatehit(int g_no);//조회수 증가
	//public abstract가 생략된 추상메서드.
	List<ComBean> getComList(int g_no);//댓글 목록
	void com_del(int comment_no);//댓글삭제
	void insertG(ComBean c);//방명록 저장
	void updateG(ComBean ec);//방명록 수정
	void delG(int g_no);//방명록 삭제
}
