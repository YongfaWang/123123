package main.java.usermanage.userManage;

import main.java.dbSQL.UserSql;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class QueryFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField sexField;
	private JTextField entryyearField;
	private JTextField passwordField;
	private JTextField searchField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public QueryFrame() {
		setResizable(false);
		setTitle("查询信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入要查找的用户的工号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(71, 0, 208, 29);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("工号：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(71, 105, 50, 30);
		panel.add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(143, 99, 240, 45);
		panel.add(idField);
		idField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("姓名：");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(71, 160, 50, 30);
		panel.add(lblNewLabel_1_1);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(143, 154, 240, 45);
		panel.add(nameField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("性别：");
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(71, 215, 50, 30);
		panel.add(lblNewLabel_1_1_1);
		
		sexField = new JTextField();
		sexField.setColumns(10);
		sexField.setBounds(143, 209, 240, 45);
		panel.add(sexField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("入职年份：");
		lblNewLabel_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(41, 270, 80, 30);
		panel.add(lblNewLabel_1_1_1_1);
		
		entryyearField = new JTextField();
		entryyearField.setColumns(10);
		entryyearField.setBounds(143, 264, 240, 45);
		panel.add(entryyearField);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("密码：");
		lblNewLabel_1_1_1_2.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1_2.setBounds(71, 325, 50, 30);
//		panel.add(lblNewLabel_1_1_1_2);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(143, 319, 240, 45);
//		panel.add(passwordField);
		
		searchField = new JTextField();
		searchField.setBounds(71, 39, 208, 45);
		panel.add(searchField);
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("查找");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// 1.读出要查询的用户输入的id
				// 因为用户的工号是唯一的，所以我们根据工号查询后，只会有一条数据
				// 转化一下数据类型
				String id = searchField.getText();
				
				// 2.执行JDBC语句
				try {
					UserSql conSql = new UserSql();
					
					User user = conSql.queryUser(id);
					
					// 3.将查询结果填到文本框中
					// 前提是用户存在
if(user != null) {
						
						// id 是直接读入的，所以就不需要从数据库中读出了
						idField.setText(String.valueOf(id));
						nameField.setText(user.getName());
						sexField.setText(user.getSex());
						entryyearField.setText(String.valueOf(user.getEntryyear()));
						passwordField.setText(user.getPassword());
					} else {
						// 此时不存在，提示用户，不存在该用户
						JOptionPane.showMessageDialog(QueryFrame.this, "无此用户");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		searchButton.setFont(new Font("宋体", Font.PLAIN, 18));
		searchButton.setBounds(289, 42, 97, 39);
		panel.add(searchButton);
	}
}

