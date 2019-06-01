package com.web.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
	private volatile static Connection conn=null;
	public static String INI_URL;
	
	public static Connection getConn() {
		if(conn==null){
			synchronized (DbHelper.class){
				if(conn==null){
					try {
						initial();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return conn;
	}

    public static void initial() throws ClassNotFoundException, SQLException, IOException{
    	DbHelper helper = new DbHelper();
//    	INI_URL="ini.txt";
//    	//从配置文件中获取数据库的url、用户名、密码
//        File f = new File(INI_URL);
//        BufferedReader reader = new BufferedReader(new FileReader(f));
//        String JDBC_DRIVER = reader.readLine().substring(5);
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    	// 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
//        String db = reader.readLine().substring(5);
//        String port = reader.readLine().substring(5);
//        String mydb = reader.readLine().substring(5);
//        String username = reader.readLine().substring(5);
//        String pwd = reader.readLine().substring(4);
//        String url = "jdbc:"+db+"://localhost:"+port+"/"+mydb+"?serverTimezone=GMT%2B8";
        String url = "jdbc:mysql://localhost:3306/movie?serverTimezone=GMT%2B8";
        
        // 打开链接
        System.out.println("连接数据库...");
        conn = DriverManager.getConnection(url,"root","110110dzp");
    }
    
    public static void close() throws SQLException{
    	if(conn!=null) conn.close();
    	System.out.println("Goodbye!");
    }
}
