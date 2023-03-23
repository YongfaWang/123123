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


public class ChangeFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField sexField;
	private JTextField entryyearField;
	private JTextField passwordField;
	private JTextField searchField;
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
	public ChangeFrame() {
		setResizable(false);
		setTitle("修改信息");
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
		
		JLabel lblNewLabel = new JLabel("请输入要修改的用户的工号：");
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
		
		JButton changeButton = new JButton("修改");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id = searchField.getText();
				
				String nameString = nameField.getText();
				
				String sexString = sexField.getText();
				
				int entryyear = Integer.parseInt(entryyearField.getText());
				
				String passwordString = passwordField.getText();
				
				UserSql conSql = new UserSql();
				
				try {
					conSql.changeUser(id, nameString, sexString, entryyear,passwordString);
					
					JOptionPane.showMessageDialog(ChangeFrame.this, "修改成功！");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(ChangeFrame.this, "修改失败！");
					e.printStackTrace();
				}
					
			}
		});
		changeButton.setFont(new Font("宋体", Font.PLAIN, 18));
		changeButton.setBounds(182, 389, 97, 33);
		panel.add(changeButton);
		
		searchField = new JTextField();
		searchField.setBounds(71, 39, 208, 45);
		panel.add(searchField);
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("查找");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				String id = searchField.getText();
				
				try {
					
					UserSql conSql = new UserSql();
					
					User user = conSql.queryUser(id);
					
					if(user != null) {
						
						// id 是直接读入的，所以就不需要从数据库中读出了
						idField.setText(String.valueOf(id));
						nameField.setText(user.getName());
						sexField.setText(user.getSex());
						entryyearField.setText(String.valueOf(user.getEntryyear()));
						passwordField.setText(user.getPassword());
						
					} else {
						JOptionPane.showMessageDialog(ChangeFrame.this, "无此用户");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		searchButton.setFont(new Font("宋体", Font.PLAIN, 18));
		searchButton.setBounds(289, 42, 97, 39);
		panel.add(searchButton);
	}
}
 