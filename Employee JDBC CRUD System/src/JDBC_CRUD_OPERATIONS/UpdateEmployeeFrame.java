package JDBC_CRUD_OPERATIONS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmpId, textFieldEmpName, textFieldSalary, textFieldEmail, textFieldPhone,
			textFieldAddress;
	private JButton btnUpdateEmployee, btnSubmit;
	private JLabel lblEmployeeDetails;

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/employee_database";
	private static final String USER = "root";
	private static final String PASSWORD = "7090428655";

	public UpdateEmployeeFrame() {
		setTitle("Update Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(9, 2, 10, 10));
		setContentPane(contentPane);

		JLabel lblEmpId = new JLabel("Enter Employee ID to Update:");
		lblEmpId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpId.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblEmpId);
		textFieldEmpId = new JTextField();
		textFieldEmpId.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldEmpId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textFieldEmpId);

		JLabel lblEmpName = new JLabel("Employee Name:");
		lblEmpName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmpName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEmpName);
		textFieldEmpName = new JTextField();
		textFieldEmpName.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldEmpName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textFieldEmpName);

		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblSalary);
		textFieldSalary = new JTextField();
		textFieldSalary.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldSalary.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textFieldSalary);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEmail);
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textFieldEmail);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblPhone);
		textFieldPhone = new JTextField();
		textFieldPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldPhone.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textFieldPhone);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAddress);
		textFieldAddress = new JTextField();
		textFieldAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldAddress.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textFieldAddress);

		btnUpdateEmployee = new JButton("Update Employee");
		btnUpdateEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdateEmployee.setEnabled(false);
		contentPane.add(new JLabel());
		contentPane.add(btnUpdateEmployee);

		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSubmit.setEnabled(false);
		contentPane.add(new JLabel());
		contentPane.add(btnSubmit);

		textFieldEmpId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empIdStr = textFieldEmpId.getText();
				if (!empIdStr.isEmpty()) {
					try {
						int empId = Integer.parseInt(empIdStr);
						loadEmployeeDetails(empId);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(UpdateEmployeeFrame.this, "Invalid employee ID format.",
								"Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empIdStr = textFieldEmpId.getText();
				if (empIdStr.isEmpty()) {
					JOptionPane.showMessageDialog(UpdateEmployeeFrame.this, "Please enter an employee ID.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int empId = Integer.parseInt(empIdStr);
						updateEmployeeDetails(empId);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(UpdateEmployeeFrame.this, "Invalid employee ID format.",
								"Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(UpdateEmployeeFrame.this, "Employee details updated successfully!",
						"Success", JOptionPane.INFORMATION_MESSAGE);
				resetFields();
				btnSubmit.setEnabled(false);
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

					btnUpdateEmployee.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this, "No employee found with ID: " + empId, "Error",
							JOptionPane.ERROR_MESSAGE);
					resetFields();
					btnUpdateEmployee.setEnabled(false);
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

	private void updateEmployeeDetails(int empId) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String query = "UPDATE employee SET emp_name = ?, emp_salary = ?, emp_email = ?, emp_phone = ?, emp_address = ? WHERE emp_id = ?";
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, textFieldEmpName.getText());
				stmt.setDouble(2, Double.parseDouble(textFieldSalary.getText()));
				stmt.setString(3, textFieldEmail.getText());
				stmt.setString(4, textFieldPhone.getText());
				stmt.setString(5, textFieldAddress.getText());
				stmt.setInt(6, empId);

				int rowsAffected = stmt.executeUpdate();

				if (rowsAffected > 0) {
					btnSubmit.setEnabled(true);
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
					UpdateEmployeeFrame frame = new UpdateEmployeeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
