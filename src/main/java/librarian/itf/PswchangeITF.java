package main.java.librarian.itf;

import main.java.dbSQL.MyInfoSQL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PswchangeITF extends JFrame implements ActionListener {
    private JPanel pan, pan1, pan2;
    private JLabel labold, labnew, labagain;
    private JPasswordField tfold, tfnew, tfagain;
    private JButton btn;
    private String id;
    public PswchangeITF(String id) {
        super("密码修改");
        this.id = id;
        pan = new JPanel(new BorderLayout(5,5));
        pan.setBorder(new EmptyBorder(10,10,10,10));
        this.setContentPane(pan);
        pan1 = new JPanel();
        pan2 = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        pan1.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();

        labold = new JLabel("请输入旧密码");
        tfold = new JPasswordField(15);
        labnew = new JLabel("请输入新密码");
        tfnew =  new JPasswordField(15);
        labagain = new JLabel("再次输入新密码");
        tfagain = new JPasswordField(15);
        btn = new JButton("修改");
        btn.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 5, 10);
        c.gridx = 1;
        c.gridy = 1;
        pan1.add(labold,c);

        c.gridx = 2;
        pan1.add(tfold,c);

        c.gridx = 1;
        c.gridy = 2;
        pan1.add(labnew,c);

        c.gridx = 2;
        pan1.add(tfnew,c);

        c.gridx = 1;
        c.gridy = 3;
        pan1.add(labagain,c);

        c.gridx = 2;
        pan1.add(tfagain,c);

        pan2.add(btn);

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
        String old = new String(tfold.getPassword());
        String newp = new String(tfnew.getPassword());
        String again  = new String(tfagain.getPassword());
        MyInfoSQL mysql = new MyInfoSQL();
        String psw = mysql.getPsw(id);
        //旧密码比较、长度限制、不为空、两次输入一致
        if (!old.equals(psw)) {
            JOptionPane.showMessageDialog(null,"旧密码不正确");
        } else if (newp == null || newp.isEmpty()) {
            JOptionPane.showMessageDialog(null,"密码不为空");
        } else if (newp.length() >=20 || again.length() >=20) {
            JOptionPane.showMessageDialog(null,"新密码输入过长");
        } else if (!newp.equals(again)) {
            JOptionPane.showMessageDialog(null,"两次输入不一致");
        } else {
            int i = mysql.pswChange(id, newp);
            if (i == 1) {
                JOptionPane.showMessageDialog(null,"修改成功");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null,"修改失败");
            }
        }
    }
}
