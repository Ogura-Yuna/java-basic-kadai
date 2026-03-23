package kadai_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Kadai {
	
	static String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	static String user = "sa";
	static String password = "";
	
	static Scanner scanner;

	public static void main(String[] args) {
		
		userCreate();
		
		boolean loopFlg = true;
		
		scanner = new Scanner(System.in);
		
		while(loopFlg) {
		
			System.out.println("1: 一覧表示");
			System.out.println("2: 登録");
			System.out.println("3: 更新");
			System.out.println("4: 削除");
			System.out.println("0: 終了");
			System.out.println("操作を選択してください > ");
				
			//入力した内容を取得する
			int input = scanner.nextInt();
			
			switch(input) {
			case 0: 
				//Scannerクラスのオブジェクトをクローズする
				scanner.close();
				loopFlg = false;
				break;
			case 1:
				userSelect();
				break;
			case 2:
				userInsert();
				break;
			case 3:
				userUpdate();
				break;
			case 4:
				userDelete();
				break;
			}
			
		}
	}
	
	public static void userCreate() {
		try (
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement()
			) {
				stmt.execute("""
						CREATE TABLE users (
							id INT PRIMARY KEY,
							name VARCHAR(100),
							age INT
						)
					""");
			} catch (Exception e) {
				e.printStackTrace();
			
			}
	}
		
	public static void userSelect() {
		
		try (
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement()
			) {
				try (PreparedStatement ps = 
						conn.prepareStatement("SELECT id, name, age FROM users ORDER BY id");
							ResultSet rs = ps.executeQuery()) {
							if (!rs.next()) {
						        // データが0件の場合の処理
								System.out.println("会員情報がありません。");
						    } else {
						        // データが存在する場合の処理
						    	System.out.println("--- 会員一覧 ---");
						        do {
						        	System.out.println(rs.getInt("id") + " / " + rs.getString("name") + " / " + rs.getString("age"));
						        } while (rs.next());
						    }
						}
			} catch (Exception e) {
				e.printStackTrace();
			
			}
			
		
	}
	
	public static void userInsert() {
		
		System.out.println("登録するIDを入力してください:");
		int insertId = scanner.nextInt();
		
		System.out.println("名前を入力してください:");
		String insertName = scanner.next();
		
		System.out.println("年齢を入力してください:");
		int insertAge = scanner.nextInt();
		
		try (
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement()
			) {
				try (PreparedStatement ps =
						conn.prepareStatement("INSERT INTO users (id, name, age) VALUES (?, ?, ?)")) {
							ps.setInt(1, insertId);
							ps.setString(2, insertName);
							ps.setInt(3, insertAge);
							ps.executeUpdate();
							System.out.println("登録しました。");
						}
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		
	}
	
	public static void userUpdate() {
		
		System.out.println("更新するIDを入力してください:");
		int updateId = scanner.nextInt();
		
		System.out.println("新しい名前を入力してください:");
		String updateName = scanner.next();
		
		System.out.println("新しい年齢を入力してください:");
		int updateAge = scanner.nextInt();
		
		try (
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement()
			) {
				try (PreparedStatement selectPs = 
					conn.prepareStatement("SELECT id, name, age FROM users WHERE id = ? ORDER BY id")) {
						selectPs.setInt(1, updateId);
						try (ResultSet rs = selectPs.executeQuery()) {
							if (!rs.next()) {
						        // データが0件の場合の処理
								System.out.println("指定したIDの会員は存在しません。");
						    } else {
						        // データが存在する場合の処理
						        do {
						        	try (PreparedStatement updatePs = 
											conn.prepareStatement("UPDATE users SET name= ?, age= ? WHERE id= ?")) {
								        		updatePs.setString(1, updateName);
								        		updatePs.setInt(2, updateAge);
								        		updatePs.setInt(3, updateId);
								        		updatePs.executeUpdate();
												System.out.println("更新しました。");
											}
						        } while (rs.next());
						    }
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		
	}
	
	public static void userDelete() {
		
		System.out.println("削除するIDを入力してください:");
		int deleteId = scanner.nextInt();
		
		try (
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement()
			) {
				try (PreparedStatement selectPs = 
					conn.prepareStatement("SELECT id, name, age FROM users WHERE id = ? ORDER BY id")) {
						selectPs.setInt(1, deleteId);
						try (ResultSet rs = selectPs.executeQuery()) {
							if (!rs.next()) {
						        // データが0件の場合の処理
								System.out.println("指定したIDの会員は存在しません。");
						    } else {
						        // データが存在する場合の処理
						        do {
						        	try (PreparedStatement deletePs = 
											conn.prepareStatement("DELETE FROM users WHERE id= ?")) {
								        		deletePs.setInt(1, deleteId);
								        		deletePs.executeUpdate();
												System.out.println("削除しました。");
											}
						        } while (rs.next());
						    }
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		
	}
	
	

}
