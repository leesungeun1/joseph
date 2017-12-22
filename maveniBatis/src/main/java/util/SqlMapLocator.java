package util;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapLocator {

	public static SqlMapClient getMapper(){
		  SqlMapClient sqlMapper;
		  try{
			  Reader reader=Resources.getResourceAsReader("SqlMapConfig.xml");
			  //SqlMapConfig.xml파일을 가져온다.
			  sqlMapper=SqlMapClientBuilder.buildSqlMapClient(reader);
			  //SqlMapConfig.xml의 내용을 적용한 sqlMapper객체를 생성
			  reader.close();
		  }catch(IOException e){
			  throw new RuntimeException(
					  "Something bad happened while building the SqlMapClient instance."+
					  e,e);
		  }
		  return sqlMapper;
	  }
}
