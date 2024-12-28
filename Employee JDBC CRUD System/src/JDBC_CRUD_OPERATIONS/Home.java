package JDBC_CRUD_OPERATIONS;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Home() {
		setFont(new Font("Dialog", Font.PLAIN, 18));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("EMPLOYEE MANAGEMENT SYSTEM");
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblHeading = new JLabel("CHOOSE AN OPERATION");
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHeading.setBounds(150, 50, 300, 30);
		contentPane.add(lblHeading);

		JButton btnViewEmployees = new JButton("VIEW EMPLOYEES");
		btnViewEmployees.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnViewEmployees.setBounds(40, 128, 200, 40);
		contentPane.add(btnViewEmployees);

		JButton btnViewById = new JButton("VIEW BY ID");
		btnViewById.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnViewById.setBounds(342, 128, 200, 40);
		contentPane.add(btnViewById);

		JButton btnAddEmployee = new JButton("ADD EMPLOYEE");
		btnAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddEmployee.setBounds(40, 230, 200, 40);
		contentPane.add(btnAddEmployee);

		JButton btnRemoveEmployee = new JButton("REMOVE EMPLOYEE");
		btnRemoveEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRemoveEmployee.setBounds(342, 230, 200, 40);
		contentPane.add(btnRemoveEmployee);

		JButton btnUpdateEmployee = new JButton("UPDATE EMPLOYEE");
		btnUpdateEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdateEmployee.setBounds(200, 340, 200, 40);
		contentPane.add(btnUpdateEmployee);

		btnViewEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeDetails().setVisible(true);
			}
		});

		btnViewById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewByIdFrame().setVisible(true);
			}
		});

		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEmployeeFrame().setVisible(true);
			}
		});

		btnRemoveEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemoveEmployeeFrame().setVisible(true);
			}
		});

		btnUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateEmployeeFrame().setVisible(true);
			}
		});
	}
}
