package main.java.usermanage.userManage;

import main.java.dbSQL.UserSql;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
 
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField sexField;
	private JTextField entryyearField;
	private JTextField passwordField;
	/**
	 * 
	 */
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
	/**
	 * Create the frame.
	 */
	public AddFrame() {
		// 和前面类似
		setResizable(false);
		setTitle("添加用户");
		// 这个地方，添加用户 100, 450, 470);
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
		
		JLabel TitleLabel = new JLabel("请输入新用户的信息：");
		TitleLabel.setFont(new Font("宋体", Font.BOLD, 20));
		TitleLabel.setBounds(71, 34, 220, 45);
		panel.add(TitleLabel);
		
		JLabel idLabel = new JLabel("工号：");
		idLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		idLabel.setBounds(71, 105, 50, 30);
		panel.add(idLabel);
		
		idField = new JTextField();
		idField.setBounds(143, 99, 240, 45);
		panel.add(idField);
		idField.setColumns(10);
		
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		nameLabel.setBounds(71, 160, 50, 30);
		panel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(143, 154, 240, 45);
		panel.add(nameField);
		
		JLabel sexLabel = new JLabel("性别：");
		sexLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		sexLabel.setBounds(71, 215, 50, 30);
		panel.add(sexLabel);
		
		sexField = new JTextField();
		sexField.setColumns(10);
		sexField.setBounds(143, 209, 240, 45);
		panel.add(sexField);
		
		JLabel entryyearLabel = new JLabel("入职年份：");
		entryyearLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		entryyearLabel.setBounds(41, 270, 80, 30);
		panel.add(entryyearLabel);
		
		entryyearField = new JTextField();
		entryyearField.setColumns(10);
		entryyearField.setBounds(143, 264, 240, 45);
		panel.add(entryyearField);
		
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		passwordLabel.setBounds(71, 325, 50, 30);
		panel.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(143, 319, 240, 45);
		panel.add(passwordField);
		
		JButton addButton = new JButton("添加");
		// 添加鼠标监听事件
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 先取出数据
				// getText() 返回字符串类型的
				// Integer.parseInt 数据类型转换
				String id = idField.getText();
 
				String nameString = nameField.getText();
 
				String sexString = sexField.getText();
 
				int entryyear = Integer.parseInt(entryyearField.getText());
				
				String passwordString = passwordField.getText();
 
				// 输出一下用户的信息，方便修改
				System.out.println(id + "\t" + nameString + "\t" + sexString + "\t" + entryyear + "\t" + passwordString);
				// ConSql 是自己写的一个工具类
				UserSql conSql = new UserSql();
				try {
					// 调用添加用户信息的方法
					conSql.addUser(id, nameString, sexString, entryyear, passwordString);
					// 弹出对话框，提示用户添加成功
					JOptionPane.showMessageDialog(AddFrame.this, "添加成功！");
				} catch (SQLException e1) {
					// 这里捕获一下异常，因为用户的工号是唯一的，所以当工号已经存在的时候，提示用户，该用户信息已存在
					// 异常代码为 23000
					if(e1.getSQLState().equals("23000")) {
						JOptionPane.showMessageDialog(AddFrame.this, "添加失败!该用户已存在");
					}
					e1.printStackTrace();
				}
 
			}
		});
		addButton.setFont(new Font("宋体", Font.PLAIN, 18));
		addButton.setBounds(182, 389, 97, 33);
		panel.add(addButton);
	}
} 

