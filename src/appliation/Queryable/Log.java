package appliation.Queryable;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import application.Connection.DBCon;


public class Log {
	
	private int employeeNumber;
	private String event;
	private LocalDateTime opTime;
	
	
	
	public static void Add(Log log) throws SQLException {
		Connection conn = DBCon.getDbConn();
		
		PreparedStatement q = conn.prepareStatement("INSERT INTO `nation`.`countries` (`name`, `area`, `national_day`, `country_code2`, `country_code3`, `region_id`)" + 
													"VALUES (?,?,?,?,?,?);",  
													Statement.RETURN_GENERATED_KEYS);
	
//		q.setString(1, country.name);
//		q.setDouble(2, country.area);
//		q.setDate(3,  Date.valueOf(country.nationalDay));
//		q.setString(4,  country.cc2);
//		q.setString(5, country.cc3);
//		q.setInt(6, 1);
//		
//		q.executeUpdate();
//		ResultSet genKey = q.getGeneratedKeys();
//		genKey.next();
//		int insertID = genKey.getInt(1);
//		
//		country.countryID = insertID;

	}
	
	
	
	
}
