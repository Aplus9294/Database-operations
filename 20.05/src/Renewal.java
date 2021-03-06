import java.sql.*;

public class Renewal { // 创建类
	static Connection con; // 声明Connection对象
	static PreparedStatement sql; // 声明PreparedStatement对象
	static ResultSet res; // 声明ResultSet对象

	public Connection getConnection() {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/" + "db_jdbc", "sa", "78711116814ZKybb");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
		Renewal c = new Renewal(); // 创建本类对象
		con = c.getConnection(); // 调用连接数据库方法
		try {
			sql = con.prepareStatement("select * from tb_wife"); // 查询数据库
			res = sql.executeQuery(); // 执行SQL语句
			System.out.println("执行增加、修改、删除前数据:");
			while (res.next()) {
				String id = res.getString(1);
				String name = res.getString("name");
				String sex = res.getString("sex");
				String birthday = res.getString("birthday"); // 遍历查询结果集
				System.out.print("编号：" + id);
				System.out.print(" 姓名：" + name);
				System.out.print(" 性别:" + sex);
				System.out.println(" 生日：" + birthday);
			}
			sql = con.prepareStatement("insert into tb_wife values(?,?,?,?)");
			sql.setString(1, "7"); // 预处理添加数据
			sql.setString(2, "金梦妮");
			sql.setString(3, "女");
			sql.setString(4, "19940615");
			sql.executeUpdate();
			sql = con.prepareStatement("update tb_wife set birthday " + "= ? where id = 4");
			sql.setString(1, "199未知"); // 更新数据
			sql.executeUpdate();
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate("delete from tb_wife where id = " + "(select max(id)from tb_wife)");
			// 查询修改数据后的tb_stu表中数据
			sql = con.prepareStatement("select * from tb_wife");
			res = sql.executeQuery(); // 执行SQL语句
			System.out.println("执行增加、修改、删除后的数据:");
			while (res.next()) {
				String id = res.getString(1);
				String name = res.getString("name");
				String sex = res.getString("sex");
				String birthday = res.getString("birthday");
				System.out.print("编号：" + id);
				System.out.print(" 姓名：" + name);
				System.out.print(" 性别:" + sex);
				System.out.println(" 生日：" + birthday);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
