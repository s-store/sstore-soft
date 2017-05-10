package org.voltdb.jdbc;

import java.sql.*;

/**
 * Example to test JDBC code. needs to be run after loading benchmark data.
 */
public class JDBCClientExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Class.forName("org.voltdb.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		Connection conn = null;
		Statement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
        try {
            
            conn = DriverManager.getConnection("jdbc:voltdb://localhost:21212");
            long startTime = System.currentTimeMillis();
            String sql1 = "select count(*) from sfltojson_tbl";
            stmt1 = conn.createStatement();
            ResultSet results1 = stmt1.executeQuery(sql1);
            long endTime = System.currentTimeMillis();
            System.out.println("time duration is: " + (endTime - startTime));
            System.out.println("query 1 result: ");
//            System.out.println(results1.getInt(1));
            while(results1.next()) {    
        	System.out.println(results1.getInt(1));
            }
            
          String sql3 = "{call GetTracksInRange(?, ?, ?, ?)}";
          stmt3 = conn.prepareCall(sql3);
          stmt3.setObject(1, new String("21"));
          stmt3.setObject(2, new Double(22));
          stmt3.setObject(3, new Double(-159));
          stmt3.setObject(4, new Double(-158));
          results1 = stmt3.executeQuery();
          System.out.println("Query 3 results:");
          while(results1.next()) {    
          	System.out.println("SP QUERY: " + results1.getString(1));
              }
            /**
          String sql2 = "{call GetJSONDataFull}";
          stmt2 = conn.prepareCall(sql2);
          results1 = stmt2.executeQuery();
          System.out.println("Query 2 results:");
          while(results1.next()) {    
          	System.out.println("SP QUERY: " + results1.getString(1));
              }*/
            
//            
//            
//            String sql2 = "Select * from contestants where contestant_number = ?";
//            stmt2 = conn.prepareStatement(sql2);
//            stmt2.setInt(1, 10);
//            ResultSet results2 = stmt2.executeQuery();
//            System.out.println("query 2 result: ");
//            while(results2.next()) {  
//                System.out.println(results2.getString(2));
//            }
            
//            String sql3 = "{call @ExtractionRemote(?, ?, ?)}";
//            stmt3 = conn.prepareCall(sql3);
//            stmt3.setInt(1, 0);
//            stmt3.setString(2, "dimtrade");
//            stmt3.setString(3, "csv");
//            stmt3.executeQuery();
            
            
//            DatabaseMetaData metaData = conn.getMetaData();
//            ResultSet result3 = metaData.getColumns(null, null, "CONTESTANTS", null);
//            System.out.println("query 3 result: ");
//            while (result3.next()) {
//                System.out.println(result3.getString("COLUMN_NAME"));
//            }
//            
//            ResultSet result4 = metaData.getColumns(null, null, "contestants", null);
//            System.out.println("query 4 result: ");
//            while (result4.next()) {
//                System.out.println(result4.getString("COLUMN_NAME") + " " + result4.getString("TYPE_NAME") + "   "  
//                        + result4.getBoolean("IS_NULLABLE") + " " + result4.getInt("RELATIVE_INDEX") 
//                        + " " +result4.getInt("COLUMN_SIZE"));
//            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null) {
                    stmt1.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
            } catch (SQLException e) {
                
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.print("GOODBYE!");
        }
		
	}

}
