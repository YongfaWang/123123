package main.java.reader.view;

import main.java.reader.bean.Rd;
import main.java.reader.service.BFormService;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReaderID extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderID frame = new ReaderID();
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
	public ReaderID() {
		setTitle("ID创建");
		setBounds(100, 100, 449, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 433, 50);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("读者ID创建");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(73, 77, 280, 38);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 150, 38);
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("请输入您的姓名：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		panel_3.add(lblNewLabel_1, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(150, 0, 200, 38);
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel_4.add(textField, BorderLayout.CENTER);
		textField.setColumns(6);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(0, 203, 433, 61);
		contentPane.add(panel_1_2);
		panel_1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("创建");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                      Rd r=new Rd();
					  r.setReaderID(textField.getText().trim());
					  BFormService BFormService=new BFormService();
					  Rd r2=BFormService.createReader(r);
					  if (r2.getReaderID()!=null){
						  String idmessage = "您的ID为" + r2.getReaderID()+ "，请谨记";
						  JOptionPane.showMessageDialog(null,idmessage);
						  ReaderID.this.dispose();
					  }else {
						  JOptionPane.showMessageDialog(null,"创建失败！");
					  }

			}
		});
		panel_1_2.add(btnNewButton);


		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
