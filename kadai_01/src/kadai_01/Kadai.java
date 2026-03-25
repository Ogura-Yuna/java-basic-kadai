package kadai_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Kadai {
	
	static String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	static String user = "sa";
	static String password = "";
	
	static List<User> users;
	
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
			System.out.println("5: サブメニュー");
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
				
				case 5:
					userList();
					break;
			}
			
		}
	}
	
	public static void userList() {
		
		boolean loopFlg = true;
		
		// 会員情報取得
		userSelect();
		
		if(users != null && !users.isEmpty()) {	
			while(loopFlg) {
			
				System.out.println("--- 追加課題メニュー ---");
				System.out.println("1: 20歳以上の会員を表示");
				System.out.println("2: 会員名一覧を表示");
				System.out.println("3: 平均年齢を表示");
				System.out.println("4: 最年長の会員を表示");
				System.out.println("5: 名前順に並べて表示");
				System.out.println("6: 18歳未満の会員がいるか判定");
				System.out.println("7: 会員名をカンマ区切りで表示");
				System.out.println("8: 年齢ごとにグループ分けして表示");
				System.out.println("9: 名前ごとの件数を表示");
				System.out.println("10: 最年少の会員を表示");
				System.out.println("0: メインメニューに戻る");
				System.out.println("操作を選択してください > ");
				
				//入力した内容を取得する
				int input = scanner.nextInt();
				
				switch(input) {
					case 0: 
						// メインメニューに戻る
						loopFlg = false;
						break;
					
					case 1:
						// 20歳以上の会員を表示
						System.out.println("--- 20歳以上の会員 ---");
						users.stream().filter(user -> user.getAge() >= 20)
							.forEach(user -> System.out.println(user.id + " / " + user.name + " / " + user.age));
						break;
					
					case 2:
						// 会員名一覧を表示
						System.out.println("--- 会員名一覧 ---");
						users.stream().map(User::getName).forEach(System.out::println);
						break;
					
					case 3:
						// 平均年齢を表示
						System.out.println("平均年齢: " + users.stream().mapToInt(User::getAge).average().orElse(0.0));
						break;
					
					case 4:
						// 最年長の会員を表示
						System.out.println("--- 最年長の会員 ---");
						users.stream().max(Comparator.comparingInt(User::getAge))
							.ifPresent(user -> System.out.println(user.id + " / " + user.name + " / " + user.age));
						break;
					
					case 5:
						// 名前順に並べて表示
						System.out.println("--- 名前順一覧 ---");
						users.stream().sorted(Comparator.comparing(User::getName))
							.forEach(user -> System.out.println(user.id + " / " + user.name + " / " + user.age));
						break;
					
					case 6:
						// 18歳未満の会員がいるか判定
						boolean minorFlg = users.stream().anyMatch(user -> user.getAge() < 18);
						if(minorFlg) {
							System.out.println("18歳未満の会員がいます。");
						} else {
							System.out.println("18歳未満の会員はいません。");
						}
						break;
					
					case 7:
						// 会員名をカンマ区切りで表示
						System.out.println(users.stream().map(User::getName).collect(Collectors.joining(", ")));
						break;
					
					case 8:
						// 年齢ごとにグループ分けして表示
						users.stream().collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getName, Collectors.toList())))
							.forEach((age, names) -> System.out.println(age + "歳: " + names));
						break;
					
					case 9:
						// 名前ごとの件数を表示
						users.stream().collect(Collectors.groupingBy(User::getName, Collectors.counting()))
							.forEach((name, cnt) -> System.out.println(name + ": " + cnt + "件"));
						break;
					
					case 10:
						// 最年少の会員を表示
						System.out.println("--- 最年少の会員 ---");
						users.stream().min(Comparator.comparingInt(User::getAge))
							.ifPresent(user -> System.out.println(user.id + " / " + user.name + " / " + user.age));
						break;
				}
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
						    	users = new ArrayList<User>();
						        do {
						        	User user = new User();
						        	user.setId(rs.getInt("id"));
						            user.setName(rs.getString("name"));
						            user.setAge(rs.getInt("age"));
						            users.add(user);
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
