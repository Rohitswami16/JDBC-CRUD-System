package JDBC_CRUD_OPERATIONS;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/employee_database";
	private static final String USER = "root";
	private static final String PASSWORD = "7090428655";

	public EmployeeDetails() {
		setTitle("Employee Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		String[] columnNames = { "Emp ID", "Emp Name", "Salary", "Email", "Phone", "Address" };

		model = new DefaultTableModel(columnNames, 0);

		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setRowHeight(25);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Tahoma", Font.BOLD, 16));

		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(tablePanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton btnLoadData = new JButton("Load Employee Data");
		btnLoadData.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonPanel.add(btnLoadData);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadEmployeeData();
			}
		});
	}

	private void loadEmployeeData() {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM employee")) {

			model.setRowCount(0);

			while (resultSet.next()) {
				int empId = resultSet.getInt("emp_id");
				String empName = resultSet.getString("emp_name");
				double salary = resultSet.getDouble("emp_salary");
				String email = resultSet.getString("emp_email");
				String phone = resultSet.getString("emp_phone");
				String address = resultSet.getString("emp_address");

				model.addRow(new Object[] { empId, empName, salary, email, phone, address });
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error fetching data from database", "Database Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDetails frame = new EmployeeDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
