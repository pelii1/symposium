package hu.innobyte.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Dao {

	public static int runStoredProcedure(String storedProcedureName) {
		try {
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
			
			cstmt = con.prepareCall("{call dbo.symp_getorders()}");
			//cstmt = con.prepareCall("{select routine_name from information_schema.routines where routine_type='function'}");
			rs = cstmt.executeQuery();
			
			if (rs.next()) {
				// System.out.println(rs.getString(1));
				return rs.getObject(1) == null ? 0 : rs.getInt(1);
			}
		} catch (Exception e) {
			System.err.println(String.format("Error in SQL, Exception: %s, message: %s",e.getClass(),e.getMessage()));
		}
		return -1;
		

	}
}
