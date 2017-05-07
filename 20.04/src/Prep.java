import java.sql.*;
public class Prep { // ������Perp
	static Connection con; // ����Connection����
	static PreparedStatement sql; // ����Ԥ��������
	static ResultSet res; // �������������
	public Connection getConnection() { // �����ݿ����ӷ���
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:jtds:sqlserver://localhost:1433/" + "db_jdbc",
					"sa", "78711116814ZKybb");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con; // ����Connection����
	}
	
	public static void main(String[] args) { // ������
		Prep c = new Prep(); // �����������
		con = c.getConnection(); // ��ȡ�����ݿ������
		try {
			// ʵ����Ԥ��������
			sql = con.prepareStatement("select * from tb_wife"
					+ " where id = ?");
			sql.setInt(1, 3); // ���ò���
			res = sql.executeQuery(); // ִ��Ԥ�������
			// �����ǰ��¼���ǽ���������һ�У������ѭ����
			while (res.next()) {
				String id = res.getString(1); // ��ȡ������е�һ�е�ֵ
				String name = res.getString("name"); // ��ȡname�е���ֵ
				String sex = res.getString("sex"); // ��ȡsex�е���ֵ
				// ��ȡbirthday�е���ֵ
				String birthday = res.getString("birthday");
				System.out.print("��ţ�" + id); // �����Ϣ
				System.out.print(" ������" + name);
				System.out.print(" �Ա�:" + sex);
				System.out.println(" ���գ�" + birthday);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}