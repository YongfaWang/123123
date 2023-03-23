package main.java;

import main.java.dbSQL.MainLogInSQL;
import main.java.librarian.itf.LibrarianMain;
import main.java.login.dataClass.Account;
import main.java.reader.view.ReaderMenu;
import main.java.usermanage.userManage.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame implements ActionListener {
    private JPanel pan, pan1, pan2;
    private JLabel labID, labpsw;
    private JTextField tfID;
    private JPasswordField tfpsw;
    private JButton btnLogin, btnVisitor;
    public LogIn() {
        super("图书室管理系统");
        pan = new JPanel(new BorderLayout(5,5));
        pan.setBorder(new EmptyBorder(10,10,10,10));
        this.setContentPane(pan);
        pan1 = new JPanel();
        pan2 = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        pan1.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();

        labID = new JLabel("用户ID：");
        tfID = new JTextField(15);
        labpsw = new JLabel("用户密码：");
        tfpsw = new JPasswordField(15);
        btnLogin = new JButton("管理员登录");
        btnVisitor = new JButton("读者点击直接进入");
        btnLogin.addActionListener(this);
        btnVisitor.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 5, 10);
        c.gridx = 1;
        c.gridy = 1;
        pan1.add(labID,c);

        c.gridx = 2;
        pan1.add(tfID,c);

        c.gridx = 1;
        c.gridy = 2;
        pan1.add(labpsw,c);

        c.gridx = 2;
        pan1.add(tfpsw,c);

        pan2.add(btnLogin);
        pan2.add(btnVisitor);

        pan.add(pan1,BorderLayout.CENTER);
        pan.add(pan2,BorderLayout.SOUTH);
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new LogIn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String id = tfID.getText();
            String psw = tfpsw.getText();
            Account u1 = new Account(id, psw);
            System.out.println(u1.getId());
            System.out.println(u1.getPsw());
            MainLogInSQL loginsql = new MainLogInSQL();
            int i = loginsql.checkUserId(u1);
            if (i == 1) {
                int k = loginsql.checkUserPsw(u1);
                if (k == 1) {
                    int t = loginsql.getType(u1);
                    System.out.println("t="+t);
                    if (t == 0) {
                        MainFrame m = new MainFrame();
                        m.main();
                        dispose();//type 0 SysMain
                    } else if (t == 1) {
                        new LibrarianMain(u1.getId());
                        dispose();//type 1 new LibrarianMain();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "用户不存在！");
            }

        }

        if (e.getSource() == btnVisitor) {
            ReaderMenu bf = new ReaderMenu();
            bf.setVisible(true);
            dispose();
        }
    }
}
