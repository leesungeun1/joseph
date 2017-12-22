package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import model.BoardBean;
import util.SqlMapLocator;
//import model.BbsBean;

public class BoardDAOImpl {

	/*Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	String sql = null;*/

	/*
	 * public BoardDAOImpl(){ try{ Context ctx=new InitialContext();
	 * ds=(DataSource)ctx.lookup("java:comp/env/jdbc/orcl"); }catch(Exception
	 * e){ e.printStackTrace(); } }
	 */
	/* 게시판 저장 */
	/*
	 * public void insertBoard(BoardBean board) { try{ con=ds.getConnection();
	 * sql="insert into board53 (board_num,board_name,board_pass,"
	 * +"board_subject,board_content,board_re_ref,board_re_lev,"
	 * +"board_re_seq,board_readcount,board_date) values("
	 * +"board53_num_seq.nextval,?,?,?,?,board53_num_seq.nextval,0,0,"
	 * +"0,sysdate)"; pstmt=con.prepareStatement(sql);
	 * pstmt.setString(1,board.getBoard_name());
	 * pstmt.setString(2,board.getBoard_pass());
	 * pstmt.setString(3,board.getBoard_subject());
	 * pstmt.setString(4,board.getBoard_content()); pstmt.executeUpdate();//삽입문
	 * 실행
	 * 
	 * pstmt.close(); con.close(); }catch(Exception e){ e.printStackTrace(); } }
	 */
	/* 게시판 저장 */
	public void insertBoard(BoardBean b) throws SQLException {
		SqlMapLocator.getMapper().insert("board_insert", b);
	}

	/* 게시물 목록 */
	/*
	 * public List<BoardBean> getBoardList(int page, int limit) { String
	 * board_list_sql="select * from "+
	 * "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"+
	 * "BOARD_CONTENT,BOARD_RE_REF,BOARD_RE_LEV,"+
	 * "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+
	 * "(select * from board53 order by BOARD_RE_REF desc,BOARD_RE_SEQ asc)) "+
	 * "where rnum>=? and rnum<=?";
	 * 
	 * List<BoardBean> list = new ArrayList<BoardBean>();
	 * 
	 * int startrow=(page-1)*10+1; //읽기 시작할 row 번호. int endrow=startrow+limit-1;
	 * //읽을 마지막 row 번호. try{ con = ds.getConnection(); pstmt =
	 * con.prepareStatement(board_list_sql); pstmt.setInt(1, startrow);
	 * pstmt.setInt(2, endrow); rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()){ BoardBean board = new BoardBean();
	 * board.setBoard_num(rs.getInt("board_num"));
	 * board.setBoard_name(rs.getString("board_name"));
	 * board.setBoard_subject(rs.getString("board_subject"));
	 * board.setBoard_content(rs.getString("board_content"));
	 * board.setBoard_re_ref(rs.getInt("board_re_ref"));
	 * board.setBoard_re_lev(rs.getInt("board_re_lev"));
	 * board.setBoard_re_seq(rs.getInt("board_re_seq"));
	 * board.setBoard_readcount(rs.getInt("board_readcount"));
	 * board.setBoard_date(rs.getString("board_date").substring(0,10));
	 * list.add(board); }
	 * 
	 * rs.close(); pstmt.close(); con.close(); }catch(Exception ex){
	 * ex.printStackTrace(); } return list; }
	 */
	/* 게시물 목록 */
	public List<BoardBean> getBoardList(int  page)	throws SQLException {
		List<BoardBean>  list = SqlMapLocator.getMapper().queryForList("board_list", page);
	    return list;
	}

	
	/* 게시판 총 게시물 수 */
	public int getListCount() throws SQLException {
		int count = 0;
		count = ((Integer) SqlMapLocator.getMapper().queryForObject(
				"board_count")).intValue();
		// intValue()메서드는 정수형 숫자로 바꿔주는 메서드
		/*
		 * ibatis에서 실행되는 메서드 1.queryForList()메서드는 한개이상 검색되는 레코드를 실행해서 컬렉션에 저장할때
		 * 주로 사용 2.queryForObject()메서드는 한개 레코드를 검색할때 사용 3. 수정은 update()메서드를 사용
		 * 4. 삭제는 delete()메서드를 사용 5. 저장은 insert()메서드를 사용
		 */
		return count;
	}

	/* 게시판 글내용보기 */
	public BoardBean getBoardCont(int board_num) throws SQLException {
		return (BoardBean) SqlMapLocator.getMapper().queryForObject(
				"board_cont", board_num);
	}

	/* 게시판 조회수 증가 */
	public void boardHit(int board_num) throws SQLException {
		SqlMapLocator.getMapper().update("board_hit", board_num);
	}

	/* 게시물 수정 */
	public void boardEdit(BoardBean b) throws SQLException {
		SqlMapLocator.getMapper().update("board_edit", b);
	}

	/* 게시물 삭제 */
	public void boardDelete(int board_num) throws SQLException {
		SqlMapLocator.getMapper().delete("board_del", board_num);
	}

	/* 답변글 레벨 증가 */
	public void refEdit(BoardBean b) throws SQLException {
		SqlMapLocator.getMapper().update("board_Level", b);
	}

	/* 답변글 저장 */
	public void boardReplyOk(BoardBean b) throws SQLException {
		SqlMapLocator.getMapper().insert("board_reply", b);
	}
}
