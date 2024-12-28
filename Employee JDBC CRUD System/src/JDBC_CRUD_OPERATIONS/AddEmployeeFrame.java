
package JDBC_CRUD_OPERATIONS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddEmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmpId;
	private JTextField textFieldEmpName;
	private JTextField textFieldSalary;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private JTextField textFieldAddress;

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/employee_database";
	private static final String USER = "root";
	private static final String PASSWORD = "7090428655";

	public AddEmployeeFrame() {
		setTitle("Add Employee");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 550);

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

		JLabel lblEmpId = new JLabel("Employee ID:");
		lblEmpId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpId.setFont(new Font("Tahoma", Font.BOLD, 16));
		formPanel.add(lblEmpId);
		textFieldEmpId = new JTextField();
		textFieldEmpId.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldEmpId.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(textFieldEmpId);

		JLabel lblEmpName = new JLabel("Employee Name:");
		lblEmpName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpName.setFont(new Font("Tahoma", Font.BOLD, 16));
		formPanel.add(lblEmpName);
		textFieldEmpName = new JTextField();
		textFieldEmpName.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldEmpName.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(textFieldEmpName);

		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(lblSalary);
		textFieldSalary = new JTextField();
		textFieldSalary.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldSalary.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(textFieldSalary);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		formPanel.add(lblEmail);
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(textFieldEmail);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(lblPhone);
		textFieldPhone = new JTextField();
		textFieldPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldPhone.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(textFieldPhone);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		formPanel.add(lblAddress);
		textFieldAddress = new JTextField();
		textFieldAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldAddress.setHorizontalAlignment(SwingConstants.CENTER);
		formPanel.add(textFieldAddress);

		contentPane.add(formPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonPanel.add(btnAddEmployee);

		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empIdStr = textFieldEmpId.getText();
				String empName = textFieldEmpName.getText();
				String salaryStr = textFieldSalary.getText();
				String email = textFieldEmail.getText();
				String phone = textFieldPhone.getText();
				String address = textFieldAddress.getText();

				if (empIdStr.isEmpty() || empName.isEmpty() || salaryStr.isEmpty() || email.isEmpty() || phone.isEmpty()
						|| address.isEmpty()) {
					JOptionPane.showMessageDialog(AddEmployeeFrame.this, "Please fill all fields.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int empId = Integer.parseInt(empIdStr);
						double salary = Double.parseDouble(salaryStr);
						addEmployeeToDatabase(empId, empName, salary, email, phone, address);

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(AddEmployeeFrame.this, "Invalid input format.", "Input Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	private void addEmployeeToDatabase(int empId, String empName, double salary, String email, String phone,
			String address) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String query = "INSERT INTO employee (emp_id, emp_name, emp_salary, emp_email, emp_phone, emp_address) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setInt(1, empId);
				stmt.setString(2, empName);
				stmt.setDouble(3, salary);
				stmt.setString(4, email);
				stmt.setString(5, phone);
				stmt.setString(6, address);

				int rowsAffected = stmt.executeUpdate();

				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(this, "Employee added successfully!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Failed to add employee.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Duplicate entries are not allowed. Please provide a different ID.",
					"Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployeeFrame frame = new AddEmployeeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
