package main.java.librarian.itf;

import main.java.LogIn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianMain extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JPanel pan;
    private JLabel lab1;
    private JMenu infomenu, borrowmenu,myinfomenu;
    private JMenuItem addmi, adminmi, readermi, borrowmi, returnmi,pswmi,infomi,exitmi;
    String id;

    public LibrarianMain(String id) {
        super("图书管理系统(图书管理员)");
        this.id = id;
        menuBar = new JMenuBar();
        infomenu = new JMenu("信息管理");
        borrowmenu = new JMenu("借阅管理");
        myinfomenu = new JMenu("个人中心");
        addmi = new JMenuItem("图书入库");
        adminmi = new JMenuItem("图书信息修改与删除");
        borrowmi = new JMenuItem("图书借阅");
        returnmi = new JMenuItem("图书归还");
        readermi = new JMenuItem("借阅查询");
        infomi = new JMenuItem("个人信息");
        pswmi = new JMenuItem("修改密码");
        exitmi = new JMenuItem("退出登录");

        menuBar.add(infomenu);
        menuBar.add(borrowmenu);
        menuBar.add(myinfomenu);
        infomenu.add(addmi);
        infomenu.add(adminmi);
        borrowmenu.add(borrowmi);
        borrowmenu.add(returnmi);
        borrowmenu.add(readermi);
        myinfomenu.add(infomi);
        myinfomenu.add(pswmi);
        myinfomenu.add(exitmi);

        addmi.addActionListener(this);
        adminmi.addActionListener(this);
        borrowmi.addActionListener(this);
        returnmi.addActionListener(this);
        readermi.addActionListener(this);
        infomi.addActionListener(this);
        pswmi.addActionListener(this);
        exitmi.addActionListener(this);

        pan = new JPanel(new BorderLayout(5,5));
        pan.setBorder(new EmptyBorder(10,10,10,10));
        this.setContentPane(pan);
        lab1 = new JLabel("工号："+id,JLabel.HORIZONTAL);
        lab1.setSize(40,40);
        lab1.setFont(new Font("宋体", Font.PLAIN, 25));
        pan.add(lab1,BorderLayout.CENTER);

        setJMenuBar(menuBar);
        setSize(950,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addmi) {
            new BooksAddITF();
        }
        if (e.getSource() == adminmi ) {
            new BooksQMDITF();
        }
        if (e.getSource() == borrowmi) {
            new NewBorrITF(id);
        }
        if (e.getSource() == returnmi ) {
            new NewRetITF();
        }
        if (e.getSource() == readermi ) {
            new BorrowITF();
        }
        if (e.getSource() == infomi ) {
            new MyInfoITF(id);
        }
        if (e.getSource() == pswmi ) {
            new PswchangeITF(id);
        }
        if (e.getSource() == exitmi) {
            new LogIn();
            dispose();
        }
    }

}
