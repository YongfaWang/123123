package main.java.librarian.itf;

import main.java.librarian.dataClass.Borrow;
import main.java.dbSQL.BorrowSQL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewRetITF extends JFrame implements ActionListener {
    private JPanel pan, pan1, pan2;
    private JLabel labisbn, labreaderID;
    private JTextField tfisbn,tfid;
    private JButton btn, btnClear;
    public NewRetITF() {
        super("图书归还");
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
        btn = new JButton("归还");
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

        pan2.add(btn);
        pan2.add(btnClear);

        pan.add(pan1,BorderLayout.CENTER);
        pan.add(pan2,BorderLayout.SOUTH);
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new NewRetITF();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            Borrow r1 = new Borrow();
            r1.setIsbn(tfisbn.getText().trim());
            r1.setID(Integer.parseInt(tfid.getText().trim()));

            BorrowSQL retsql = new BorrowSQL();
            int i = retsql.RetBooks(r1);
            if (i == 1) {
                retsql.BorrowMinus(r1);
                JOptionPane.showMessageDialog(null, "归还成功！");
            } else {
                JOptionPane.showMessageDialog(null, "无借阅记录，请重试！");
            }
        }
        if (e.getSource() == btnClear) {
            tfisbn.setText("");
            tfid.setText("");
        }
    }
}
