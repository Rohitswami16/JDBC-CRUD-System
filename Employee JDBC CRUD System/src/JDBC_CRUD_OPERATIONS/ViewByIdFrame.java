package JDBC_CRUD_OPERATIONS;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewByIdFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmpId;
	private JTable table;
	private DefaultTableModel model;

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/employee_database";
	private static final String USER = "root";
	private static final String PASSWORD = "7090428655";

	private JCheckBox chkEmpId, chkEmpName, chkSalary, chkEmail, chkPhone, chkAddress;

	public ViewByIdFrame() {
		setTitle("View Employee by ID");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		JPanel panelInput = new JPanel();
		panelInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panelInput);

		JLabel lblEmpId = new JLabel("Enter Employee ID:");
		lblEmpId.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInput.add(lblEmpId);

		textFieldEmpId = new JTextField();
		textFieldEmpId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEmpId.setColumns(10);
		panelInput.add(textFieldEmpId);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInput.add(btnSearch);

		JPanel panelCheckboxes = new JPanel();
		panelCheckboxes.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(panelCheckboxes);

		chkEmpId = new JCheckBox("Emp ID");
		chkEmpId.setSelected(true);
		panelCheckboxes.add(chkEmpId);

		chkEmpName = new JCheckBox("Emp Name");
		chkEmpName.setSelected(true);
		panelCheckboxes.add(chkEmpName);

		chkSalary = new JCheckBox("Salary");
		chkSalary.setSelected(true);
		panelCheckboxes.add(chkSalary);

		chkEmail = new JCheckBox("Email");
		chkEmail.setSelected(true);
		panelCheckboxes.add(chkEmail);

		chkPhone = new JCheckBox("Phone");
		chkPhone.setSelected(true);
		panelCheckboxes.add(chkPhone);

		chkAddress = new JCheckBox("Address");
		chkAddress.setSelected(true);
		panelCheckboxes.add(chkAddress);

		String[] columnNames = { "Emp ID", "Emp Name", "Salary", "Email", "Phone", "Address" };
		model = new DefaultTableModel(columnNames, 0) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Tahoma", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empIdStr = textFieldEmpId.getText();
				if (empIdStr.isEmpty()) {
					JOptionPane.showMessageDialog(ViewByIdFrame.this, "Please enter an employee ID.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int empId = Integer.parseInt(empIdStr);
						loadEmployeeById(empId);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(ViewByIdFrame.this, "Invalid employee ID format.", "Input Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	private void loadEmployeeById(int empId) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employee WHERE emp_id = ?")) {

			stmt.setInt(1, empId);

			try (ResultSet resultSet = stmt.executeQuery()) {
				model.setRowCount(0);

				if (resultSet.next()) {
					String empName = resultSet.getString("emp_name");
					double salary = resultSet.getDouble("emp_salary");
					String email = resultSet.getString("emp_email");
					String phone = resultSet.getString("emp_phone");
					String address = resultSet.getString("emp_address");

					Object[] row = new Object[6];
					row[0] = chkEmpId.isSelected() ? empId : null;
					row[1] = chkEmpName.isSelected() ? empName : null;
					row[2] = chkSalary.isSelected() ? salary : null;
					row[3] = chkEmail.isSelected() ? email : null;
					row[4] = chkPhone.isSelected() ? phone : null;
					row[5] = chkAddress.isSelected() ? address : null;

					model.addRow(row);
				} else {
					JOptionPane.showMessageDialog(this, "No employee found with ID: " + empId, "No Data",
							JOptionPane.INFORMATION_MESSAGE);
				}
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
					ViewByIdFrame frame = new ViewByIdFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

