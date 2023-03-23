package main.java.reader.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.librarian.dataClass.Book;
import main.java.reader.bean.BForm;
import main.java.reader.service.BFormService;
import main.java.reader.util.TableMode;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class BorrowingForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JComboBox comboBox = new JComboBox();
	private JTable table;
	private JButton btn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowingForm frame = new BorrowingForm();
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
	public BorrowingForm() {
		setTitle("图书查询系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 511);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("图书查询系统");
		lblNewLabel.setForeground(new Color(60, 179, 113));
		lblNewLabel.setBackground(new Color(255, 182, 193));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel.setBounds(239, 23, 124, 71);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(220, 105, 199, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("请选择：");
		lblNewLabel_1.setBounds(90, 108, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"书名", "IBSN"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(134, 104, 76, 23);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("书名")) {
					Book book=new Book();
					book.setBookName(textField.getText());
					BFormService bs=new BFormService();
					List<Book> aList=bs.select_by_id(book);
					Vector data =new Vector();
			 		Iterator<Book> iterator=aList.iterator();

					while(iterator.hasNext()){
						Book next = iterator.next();
						//用于计算stock-borrow
						String s=String.valueOf(next.getSum()-next.getBorrow());
						//
						Vector row=new Vector();
						row.add(next.getISBN());
						row.add(next.getBookName());
						row.add(next.getPublisher());
						row.add(next.getAuthor());
						row.add(s);
						row.add(next.getPrice());
						data.add(row);
						System.out.println(data);
					}

					Vector colNames=new Vector();
					colNames.add("图书ISBN号");
					colNames.add("图书名称");
					colNames.add("出版社");
					colNames.add("作者");
					colNames.add("单价");
					colNames.add("在馆册数");
					TableMode mode=new TableMode(data, colNames);
					table.setModel(mode);
				}
				else {
					Book book=new Book();
					book.setISBN(textField.getText());
					BFormService bs=new BFormService();
					List<Book> aList=bs.select_by_isbn(book);
					Vector data =new Vector();
					Iterator<Book> iterator=aList.iterator();
					while(iterator.hasNext()){
						Book next = iterator.next();
						//用于计算stock-borrow
						String s=String.valueOf(next.getSum()-next.getBorrow());
						//
						Vector row=new Vector();
						row.add(next.getISBN());
						row.add(next.getBookName());
						row.add(next.getPublisher());
						row.add(next.getAuthor());
						row.add(s);
						row.add(next.getPrice());
						data.add(row);
						System.out.println(data);
					}

					Vector colNames=new Vector();
					colNames.add("图书ISBN号");
					colNames.add("图书名称");
					colNames.add("出版社");
					colNames.add("作者");
					colNames.add("单价");
					colNames.add("在馆册数");
					TableMode mode=new TableMode(data, colNames);
					table.setModel(mode);

				}
			}
		});
		btnNewButton.setBounds(429, 104, 93, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setBounds(0, 137, 623, 10);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.gray);
		panel_1.setBounds(0, 84, 623, 10);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 147, 623, 282);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(60, 179, 113));
		panel_3.setBounds(0, 428, 623, 10);
		contentPane.add(panel_3);
		
		Book bf=new Book();
		bf.setISBN("");
		BFormService bs=new BFormService();
		List<Book> aList=bs.select_by_isbn(bf);
		Vector data =new Vector();
 		Iterator<Book> iterator=aList.iterator();
		while(iterator.hasNext()){
			Book next = iterator.next();
			//用于计算stock-borrow
			String s=String.valueOf(next.getSum()-next.getBorrow());
			//
			Vector row=new Vector();
			row.add(next.getISBN());
			row.add(next.getBookName());
			row.add(next.getPublisher());
			row.add(next.getAuthor());
			row.add(s);
			row.add(next.getPrice());
			data.add(row);
			System.out.println(data);
		}

		Vector colNames=new Vector();
		colNames.add("图书ISBN号");
		colNames.add("图书名称");
		colNames.add("出版社");
		colNames.add("作者");
		colNames.add("在馆册数");
		colNames.add("单价");
		TableMode mode=new TableMode(data, colNames);
		table.setModel(mode);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
