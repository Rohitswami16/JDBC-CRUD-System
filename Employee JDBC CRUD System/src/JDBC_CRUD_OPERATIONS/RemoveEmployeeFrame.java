package JDBC_CRUD_OPERATIONS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveEmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmpId, textFieldEmpName, textFieldSalary, textFieldEmail, textFieldPhone,
			textFieldAddress;
	private JButton btnRemoveEmployee;

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/employee_database";
	private static final String USER = "root";
	private static final String PASSWORD = "7090428655";

	public RemoveEmployeeFrame() {
		setTitle("Remove Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

		JLabel lblEmpId = new JLabel("Enter Emp ID to Remove:");
		lblEmpId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpId.setFont(new Font("Tahoma", Font.BOLD, 16));
		formPanel.add(lblEmpId);
		textFieldEmpId = new JTextField();
		textFieldEmpId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmpId.setFont(new Font("Tahoma", Font.BOLD, 16));
		formPanel.add(textFieldEmpId);

		JLabel lblEmpName = new JLabel("Employee Name:");
		lblEmpName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmpName.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(lblEmpName);
		textFieldEmpName = new JTextField();
		textFieldEmpName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmpName.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldEmpName.setEditable(false);
		formPanel.add(textFieldEmpName);

		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 16));
		formPanel.add(lblSalary);
		textFieldSalary = new JTextField();
		textFieldSalary.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSalary.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldSalary.setEditable(false);
		formPanel.add(textFieldSalary);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(lblEmail);
		textFieldEmail = new JTextField();
		textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldEmail.setEditable(false);
		formPanel.add(textFieldEmail);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		formPanel.add(lblPhone);
		textFieldPhone = new JTextField();
		textFieldPhone.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldPhone.setEditable(false);
		formPanel.add(textFieldPhone);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(lblAddress);
		textFieldAddress = new JTextField();
		textFieldAddress.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldAddress.setEditable(false);
		formPanel.add(textFieldAddress);

		contentPane.add(formPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnRemoveEmployee = new JButton("Remove Employee");
		btnRemoveEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRemoveEmployee.setEnabled(false);
		buttonPanel.add(btnRemoveEmployee);

		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		textFieldEmpId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empIdStr = textFieldEmpId.getText();
				if (!empIdStr.isEmpty()) {
					try {
						int empId = Integer.parseInt(empIdStr);
						loadEmployeeDetails(empId);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(RemoveEmployeeFrame.this, "Invalid employee ID format.",
								"Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnRemoveEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empIdStr = textFieldEmpId.getText();
				if (empIdStr.isEmpty()) {
					JOptionPane.showMessageDialog(RemoveEmployeeFrame.this, "Please enter an employee ID.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int empId = Integer.parseInt(empIdStr);
						removeEmployeeFromDatabase(empId);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(RemoveEmployeeFrame.this, "Invalid employee ID format.",
								"Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	private void loadEmployeeDetails(int empId) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employee WHERE emp_id = ?")) {

			stmt.setInt(1, empId);

			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					String empName = resultSet.getString("emp_name");
					double salary = resultSet.getDouble("emp_salary");
					String email = resultSet.getString("emp_email");
					String phone = resultSet.getString("emp_phone");
					String address = resultSet.getString("emp_address");

					textFieldEmpName.setText(empName);
					textFieldSalary.setText(String.valueOf(salary));
					textFieldEmail.setText(email);
					textFieldPhone.setText(phone);
					textFieldAddress.setText(address);

					btnRemoveEmployee.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this, "No employee found with ID: " + empId, "Error",
							JOptionPane.ERROR_MESSAGE);
					resetFields();
					btnRemoveEmployee.setEnabled(false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error fetching data from database", "Database Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resetFields() {
		textFieldEmpName.setText("");
		textFieldSalary.setText("");
		textFieldEmail.setText("");
		textFieldPhone.setText("");
		textFieldAddress.setText("");
	}

	private void removeEmployeeFromDatabase(int empId) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String query = "DELETE FROM employee WHERE emp_id = ?";
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setInt(1, empId);

				int rowsAffected = stmt.executeUpdate();

				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(this, "Employee removed successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					resetFields();
					btnRemoveEmployee.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(this, "No employee found with ID: " + empId, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error connecting to the database.", "Database Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveEmployeeFrame frame = new RemoveEmployeeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
