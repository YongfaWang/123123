package main.java.librarian.itf;

import main.java.librarian.dataClass.Borrow;
import main.java.dbSQL.BorrowSQL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewBorrITF extends JFrame implements ActionListener {
    private JPanel pan, pan1, pan2;
    private JLabel labisbn, labreaderID, labtime;
    private JTextField tfisbn,tfid, tftime;
    private JButton btn, btnClear;
    private  String userid;
    public NewBorrITF(String userid) {
        super("图书借阅");
        this.userid = userid;
        pan = new JPanel(new BorderLayout(5,5));
        pan.setBorder(new EmptyBorder(10,10,10,10));
        this.setContentPane(pan);
        pan1 = new JPanel();
        pan2 = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        pan1.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();

        labisbn = new JLabel("图书ISBN：");
        tfisbn = new JTextField(15);
        labreaderID = new JLabel("借阅者ID：");
        tfid = new JTextField(15);
        labtime = new JLabel("借阅时间(yyyy-mm-dd)：");
        tftime = new JTextField(15);
        btn = new JButton("借阅");
        btnClear = new JButton("清空输入");
        btn.addActionListener(this);
        btnClear.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 5, 10);
        c.gridx = 1;
        c.gridy = 1;
        pan1.add(labisbn,c);

        c.gridx = 2;
        pan1.add(tfisbn,c);

        c.gridx = 1;
        c.gridy = 2;
        pan1.add(labreaderID,c);

        c.gridx = 2;
        pan1.add(tfid,c);

        c.gridx = 1;
        c.gridy = 3;
        pan1.add(labtime,c);

        c.gridx = 2;
        pan1.add(tftime,c);

        pan2.add(btn);
        pan2.add(btnClear);

        pan.add(pan1,BorderLayout.CENTER);
        pan.add(pan2,BorderLayout.SOUTH);
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            Borrow r1 = new Borrow();
            r1.setIsbn(tfisbn.getText().trim());
            r1.setID(Integer.parseInt(tfid.getText().trim()));
            r1.setBorDate(tftime.getText());
            r1.setStaffID(userid);

            BorrowSQL borsql = new BorrowSQL();
            int b = borsql.check(r1);
            System.out.println("borrowing:" + b);
            //判断是否可借
            if (b >=5) {
                JOptionPane.showMessageDialog(null, "超出可借阅图书数量限制，请先归还书目！");
            } else {
                //新增借阅
                int i = borsql.BorrowBooks(r1);
                if (i == 1) {
                    //查看馆存是否满足
                    int k = borsql.BorrowAdd(r1);
                    if (k == 1) {
                        JOptionPane.showMessageDialog(null, "借阅成功！");
                    } else {
                        //删除录入记录
                        borsql.RetBooks(r1);
                        JOptionPane.showMessageDialog(null, "该书已无馆存，请重试");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "借阅失败，请重试！");
                }
            }
        }
        if (e.getSource() == btnClear) {
            tfisbn.setText("");
            tfid.setText("");
            tftime.setText("");
        }
    }

}
