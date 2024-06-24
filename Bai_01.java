
package Ket_Noi_CSDL;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Bai_01 extends JFrame implements ActionListener {
	JLabel l1, l2;
	JTextField t1, t2;
	JButton b1,b2,b3;
	JTable tb1;
	JPanel p1, p2,p3,p5;	
	Choice c1;
	public void GUI()
	{
		setTitle("Trích xuất cơ sở dữ liệu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1 = new JLabel("Input information");
		l2 = new JLabel("SQL");
		t1 = new JTextField(100);
		t2 = new JTextField(150);
		
		b1 = new JButton("Submit"); b1.addActionListener(this);
		b2 = new JButton("Reset"); b2.addActionListener(this);
		b3 = new JButton("Cancel"); b3.addActionListener(this);
		tb1 = new JTable(); tb1.setEnabled(false);
		p1 = new JPanel(new GridLayout(2,2)); 
		
		p2 = new JPanel(new FlowLayout()); p2.add(b1); p2.add(b2); p2.add(b3);
		p5 = new JPanel(new GridLayout(1,1)); 
		c1 = new Choice(); c1.add("Select"); c1.add("Insert"); c1.add("Update"); c1.add("Delete");
		p5.add(t2); p5.add(c1);
		p1.add(l1); p1.add(t1);
		p1.add(l2); p1.add(p5);
		JScrollPane srp = new JScrollPane(tb1);
	
		srp.setSize(400,300);
		p3 = new JPanel(new GridLayout(1,1));
		p3.add(srp);
		JPanel p4 = new JPanel(new BorderLayout());
		p4.add(p1, BorderLayout.NORTH);
		p4.add(p3, BorderLayout.CENTER);
		p4.add(p2, BorderLayout.SOUTH);
		
        setSize(500, 500);
        add(p4);
        setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bai_01 a = new Bai_01();
		a.GUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b2)
		{
			DefaultTableModel model = (DefaultTableModel) tb1.getModel();
			model.setRowCount(0); 
			model.setColumnCount(0);
		}
		if(e.getSource() == b3)
		{
			System.exit(0);
		}
		if(e.getSource() == b1)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				String url = t1.getText();
				if(c1.getSelectedItem().toString() == "Select")
				{
					try 
					{
						Connection con = DriverManager.getConnection(url, "root", "");
						Statement stmt = con.createStatement();
						String sql = t2.getText();
						ResultSet rs = stmt.executeQuery(sql);
						ResultSetMetaData rsmd = rs.getMetaData();
						int socot = rsmd.getColumnCount();
						String[] columnlabel = new String[socot];
						for(int j=0 ; j< socot; j++)
						{
							columnlabel[j] = rsmd.getColumnLabel(j+1);
						}
						DefaultTableModel model = new DefaultTableModel(columnlabel, 0);
						Object[] rowData = new Object[socot];
						while(rs.next())
						{
							for(int i=0; i< socot; i++)
							{
								rowData[i] = rs.getObject(i+1);
								//System.out.print(rs.getObject(i) + "    ");
							}
							model.addRow(rowData);
						}
						tb1.setModel(model);
						rs.close();
						stmt.close();
					}
					catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(c1.getSelectedItem().toString() == "Insert")
				{
					try 
					{
						Connection con = DriverManager.getConnection(url, "root", "");
						Statement stmt = con.createStatement();
						String sql = t2.getText();
						PreparedStatement preparedStatement = con.prepareStatement(sql);
						// Thực thi câu lệnh INSERT
			            int rowsAffected = preparedStatement.executeUpdate();
			            if (rowsAffected > 0) {
			                System.out.println("Dữ liệu đã được chèn thành công vào cơ sở dữ liệu.");
			            } else {
			                System.out.println("Không có dữ liệu nào được chèn vào cơ sở dữ liệu.");
			            }
						
					}
					catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
					if(c1.getSelectedItem().toString() == "Delete")
					{
						try 
						{
							Connection con = DriverManager.getConnection(url, "root", "");
							Statement stmt = con.createStatement();
							String sql = t2.getText();
							PreparedStatement preparedStatement = con.prepareStatement(sql);
					        int rowsAffected = preparedStatement.executeUpdate();
				            if (rowsAffected > 0) {
				                System.out.println("Dữ liệu đã được xóa thành công.");
				            } else {
				                System.out.println("Không có dữ liệu nào được xóa.");
				            }
						}
						catch (SQLException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(c1.getSelectedItem().toString() == "Update")
					{
						try 
						{
							Connection con = DriverManager.getConnection(url, "root", "");
							Statement stmt = con.createStatement();
							String sql = t2.getText();
							
							PreparedStatement preparedStatement = con.prepareStatement(sql);
				            int rowsAffected = preparedStatement.executeUpdate();

				            if (rowsAffected > 0) {
				                System.out.println("Dữ liệu đã được cập nhật thành công.");
				            } else {
				                System.out.println("Không có dữ liệu nào được cập nhật.");
				            }
						}
						catch (SQLException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			}
			catch (ClassNotFoundException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
