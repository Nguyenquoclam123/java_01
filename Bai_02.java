package Ket_Noi_CSDL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Bai_02 extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSearch,btnExit,btnResest;
	private JTextField txtNhap;
	private DefaultTableModel tableModel;
	private JRadioButton rdbtnMaso, rdbtnHoten,rdbtnNgaysinh,rdbtnDiachi,rdbtnGioitinh;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai_02 frame = new Bai_02();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					
					
					e.printStackTrace();
				}
			}
		});
	}
	public Bai_02() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TÌM KIẾM THÔNG TIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(87, 10, 396, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Input");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 64, 93, 19);
		contentPane.add(lblNewLabel_1);
		
		txtNhap = new JTextField();
		txtNhap.setBounds(106, 64, 203, 19);
		contentPane.add(txtNhap);
		txtNhap.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 	String sql = "";
				 	boolean isSelected = false;
				    if (txtNhap.getText().isEmpty()) 
				        JOptionPane.showMessageDialog(contentPane, "Chưa nhập thông tin tìm kiếm!", "Thông Báo", JOptionPane.ERROR_MESSAGE);
				    else {
				        if (rdbtnMaso.isSelected())
				        	{
				            sql = "select * from sinhvien where Maso = " + txtNhap.getText();
				            isSelected = true;
				        	} 
				        else if (rdbtnHoten.isSelected()) 
				        	{
				            sql = "select * from sinhvien where Hoten LIKE '%" + txtNhap.getText() + "%'";
				            isSelected = true;
				        	} 
				        else if (rdbtnNgaysinh.isSelected())
				        	{
				            sql = "select * from sinhvien where DAY(Ngaysinh) = " + txtNhap.getText();
				            isSelected = true;
				        	} 
				        else if (rdbtnDiachi.isSelected()) 
				        	{
				            sql = "select * from sinhvien where Diachi = '" + txtNhap.getText() + "'";
				            isSelected = true;
				        	} 
				        else if (rdbtnGioitinh.isSelected()) 
				        	{
				            sql = "select * from sinhvien where Gioitinh = '" + txtNhap.getText() + "'";
				            isSelected = true;
				        	}
				        if (isSelected) {
				        	loaddata(sql);
				        				} 
				        else {
				        	 JOptionPane.showMessageDialog(contentPane, "Chưa chọn phương thức tìm kiếm!", "Thông Báo", JOptionPane.ERROR_MESSAGE);
				        		}
				    	}
				    
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(319, 64, 71, 19);
		contentPane.add(btnSearch);
		
		btnResest = new JButton("Resest");
		btnResest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNhap.setText("");
				tableModel.setColumnCount(0);
				tableModel.setRowCount(0);
			}
		});
		btnResest.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnResest.setBounds(398, 64, 71, 19);
		contentPane.add(btnResest);
		
		 btnExit = new JButton("Exit");
		 btnExit.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		System.exit(0);
		 	}
		 });
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExit.setBounds(477, 64, 71, 19);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_2 = new JLabel("Search as:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 126, 93, 19);
		contentPane.add(lblNewLabel_2);
		
		rdbtnMaso = new JRadioButton("Maso");
		rdbtnMaso.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonGroup.add(rdbtnMaso);
		rdbtnMaso.setBounds(106, 126, 60, 19);
		contentPane.add(rdbtnMaso);
		
		rdbtnHoten = new JRadioButton("Hoten");
		rdbtnHoten.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonGroup.add(rdbtnHoten);
		rdbtnHoten.setBounds(185, 126, 60, 21);
		contentPane.add(rdbtnHoten);
		
		rdbtnNgaysinh = new JRadioButton("Ngaysinh");
		rdbtnNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonGroup.add(rdbtnNgaysinh);
		rdbtnNgaysinh.setBounds(268, 126, 87, 21);
		contentPane.add(rdbtnNgaysinh);
		
		rdbtnGioitinh = new JRadioButton("Gioitinh");
		rdbtnGioitinh.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonGroup.add(rdbtnGioitinh);
		rdbtnGioitinh.setBounds(458, 126, 71, 21);
		contentPane.add(rdbtnGioitinh);
		
		rdbtnDiachi = new JRadioButton("Diachi");
		rdbtnDiachi.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonGroup.add(rdbtnDiachi);
		rdbtnDiachi.setBounds(371, 126, 70, 21);
		contentPane.add(rdbtnDiachi);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(10, 244, 538, 122);
		contentPane.add(scrollPaneTable);
		
		table = new JTable();
		table.setBackground(new Color(166, 184, 158));
		scrollPaneTable.setViewportView(table);
	}
	public void loaddata(String sql) {
	    try {
	        String url = "jdbc:mysql://127.0.0.1:3306/data" ;
	        Connection con = DriverManager.getConnection(url, "root", "");
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int socot = rsmd.getColumnCount();
	        String[] columnNames = new String[socot];
	        for (int i = 0; i < socot; i++) {
	            columnNames[i] = rsmd.getColumnName(i + 1);
	        }
	        tableModel = new DefaultTableModel(columnNames, 0) {
	        	@Override
	        public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
	        while (rs.next()) {
	            Object[] row = new Object[socot];
	            for (int i = 0; i < socot; i++) {
	                row[i] = rs.getObject(i + 1);
	            }
	            tableModel.addRow(row);
	        }
	        table.setModel(tableModel);
	        rs.close();
	        st.close();
	        con.close();
	    } catch (Exception e2) {
	        System.out.println("Error: " + e2);
	    }
	}
}
