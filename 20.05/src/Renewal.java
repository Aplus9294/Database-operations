import java.sql.*;

public class Renewal { // ������
	static Connection con; // ����Connection����
	static PreparedStatement sql; // ����PreparedStatement����
	static ResultSet res; // ����ResultSet����

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
		Renewal c = new Renewal(); // �����������
		con = c.getConnection(); // �����������ݿⷽ��
		try {
			sql = con.prepareStatement("select * from tb_wife"); // ��ѯ���ݿ�
			res = sql.executeQuery(); // ִ��SQL���
			System.out.println("ִ�����ӡ��޸ġ�ɾ��ǰ����:");
			while (res.next()) {
				String id = res.getString(1);
				String name = res.getString("name");
				String sex = res.getString("sex");
				String birthday = res.getString("birthday"); // ������ѯ�����
				System.out.print("��ţ�" + id);
				System.out.print(" ������" + name);
				System.out.print(" �Ա�:" + sex);
				System.out.println(" ���գ�" + birthday);
			}
			sql = con.prepareStatement("insert into tb_wife values(?,?,?,?)");
			sql.setString(1, "7"); // Ԥ�����������
			sql.setString(2, "������");
			sql.setString(3, "Ů");
			sql.setString(4, "19940615");
			sql.executeUpdate();
			sql = con.prepareStatement("update tb_wife set birthday " + "= ? where id = 4");
			sql.setString(1, "199δ֪"); // ��������
			sql.executeUpdate();
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate("delete from tb_wife where id = " + "(select max(id)from tb_wife)");
			// ��ѯ�޸����ݺ��tb_stu��������
			sql = con.prepareStatement("select * from tb_wife");
			res = sql.executeQuery(); // ִ��SQL���
			System.out.println("ִ�����ӡ��޸ġ�ɾ���������:");
			while (res.next()) {
				String id = res.getString(1);
				String name = res.getString("name");
				String sex = res.getString("sex");
				String birthday = res.getString("birthday");
				System.out.print("��ţ�" + id);
				System.out.print(" ������" + name);
				System.out.print(" �Ա�:" + sex);
				System.out.println(" ���գ�" + birthday);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
