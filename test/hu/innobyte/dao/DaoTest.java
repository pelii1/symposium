package hu.innobyte.dao;

import static org.junit.Assert.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class DaoTest {

	@Test
	public void test() throws SQLException {
		Connection con = null;
		
		SQLServerDataSource dataSource = new SQLServerDataSource();
		dataSource.setServerName("10.35.218.31");
		dataSource.setPortNumber(1433);
		dataSource.setDatabaseName("mcdemo");
		dataSource.setPassword("mcdemo123");
		dataSource.setUser("mcdemo");
		
		con = dataSource.getConnection();
		
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		//cstmt = con.prepareCall("{call dbo.rdk_get_animindex()}");
		//cstmt = con.prepareCall("begin ? := rdk_get_animindex(); end;");
		cstmt = con.prepareCall("select routine_name,routine_type from information_schema.routines ");
		
		//cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
		//tmt.executeQuery();
		
		//ResultSetMetaData rsmd = cstmt.getMetaData();
		
		//System.out.println(rsmd.getColumnCount());
		rs = cstmt.executeQuery();
		
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}

		
		cstmt = con.prepareCall("select 'Parameter_name' = name, 'Type'   = type_name(user_type_id), 'Length'   = max_length, " +
		"'Prec'   = case when type_name(system_type_id) = 'uniqueidentifier' then precision else OdbcPrec(system_type_id, max_length, precision) end, " +
		"'Scale'   = OdbcScale(system_type_id, scale), 'Param_order'  = parameter_id, 'Collation'   = convert(sysname, case when system_type_id in (35, 99, 167, 175, 231, 239) " +  
		"then ServerProperty('collation') end) from sys.parameters where object_id = object_id('dbo.beerorders')");
		
		rs = cstmt.executeQuery();
		
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		
		System.out.println("2");
		cstmt = con.prepareCall("select 'Parameter_name' = name, 'Type'   = type_name(user_type_id), 'Length'   = max_length, " +
		"'Prec'   = case when type_name(system_type_id) = 'uniqueidentifier' then precision else OdbcPrec(system_type_id, max_length, precision) end, " +
		"'Scale'   = OdbcScale(system_type_id, scale), 'Param_order'  = parameter_id, 'Collation'   = convert(sysname, case when system_type_id in (35, 99, 167, 175, 231, 239) " +  
		"then ServerProperty('collation') end) from sys.parameters where object_id = object_id('dbo.rdk_get_animindex')");
		
		rs = cstmt.executeQuery();
		
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		
		
		
		
		
		
		
		
		//fail("Not yet implemented");
	}

}


/*
<property name="driver" value="com.microsoft.sqlserver.jdbc.SQLServerDriver" version="1.0"/>

  <property name="url" value="jdbc:sqlserver://10.35.218.31:1433;databaseName=?????????;" version="1.0"/> 
 
*/