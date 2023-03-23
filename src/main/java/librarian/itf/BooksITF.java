package main.java.librarian.itf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BooksITF extends JFrame implements ActionListener, KeyListener {
     JPanel pan, pan1, pan2;
     JLabel labisbn, labname, labauthor,labtype, labpublisher, labnum, labprice;
     JTextField tfisbn,tfname, tfauthor, tfpublisher, tfnum, tfprice;
     JComboBox cbtype;
     JButton btn;
     String[] Options = {"科学与知识", "哲学、心理学", "宗教、神学", "社会科学", "数学、自然科学", "应用科学、医学、科技", "艺术、娱乐、休闲、体育", "语言、语言学、文学", "地理、传记、历史","其它"};
     public BooksITF(String title, String num, String btn) {
        super(title);
        pan = new JPanel(new BorderLayout(5,5));
        pan.setBorder(new EmptyBorder(10,10,10,10));
        this.setContentPane(pan);
        pan1 = new JPanel();
        pan2 = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        pan1.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();

        labisbn = new JLabel("图书ISBN：");
        tfisbn = new JTextField(JTextField.LEFT);
        labname = new JLabel("图书名称：");
        tfname = new JTextField(JTextField.LEFT);
        labauthor = new JLabel("图书作者：");
        tfauthor = new JTextField(JTextField.LEFT);
        labtype = new JLabel("图书类别：");
        cbtype = new JComboBox();
        for (int i = 0; i < 10; i++) {
            cbtype.addItem(Options[i]);
        }
        labpublisher = new JLabel("图书出版社：");
        tfpublisher = new JTextField(JTextField.LEFT);

        labnum = new JLabel(num);
        tfnum = new JTextField(JTextField.LEFT);
        tfnum.addKeyListener(this);

        labprice = new JLabel("图书价格(元/本)");
        tfprice = new JTextField(JTextField.LEFT);
        tfprice.addKeyListener(this);

        this.btn = new JButton(btn);
        this.btn.addActionListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 5, 10);
        c.gridx = 1;
        c.gridy = 1;
        pan1.add(labisbn,c);

        c.gridx = 2;
        pan1.add(tfisbn,c);

        c.gridx = 1;
        c.gridy = 2;
        pan1.add(labname,c);

        c.gridx = 2;
        pan1.add(tfname,c);

        c.gridx = 1;
        c.gridy = 3;
        pan1.add(labauthor,c);

        c.gridx = 2;
        pan1.add(tfauthor,c);

        c.gridx = 1;
        c.gridy = 4;
        pan1.add(labtype,c);

        c.gridx = 2;
        pan1.add(cbtype,c);

        c.gridx = 1;
        c.gridy = 5;
        pan1.add(labpublisher,c);

        c.gridx = 2;
        pan1.add(tfpublisher,c);

        c.gridx = 1;
        c.gridy = 6;
        pan1.add(labnum,c);

        c.gridx = 2;
        pan1.add(tfnum,c);

        c.gridx = 1;
        c.gridy = 7;
        pan1.add(labprice,c);

        c.gridx = 2;
        pan1.add(tfprice,c);

        pan2.add(this.btn);

        pan.add(pan1,BorderLayout.CENTER);
        pan.add(pan2,BorderLayout.SOUTH);
        setSize(400,350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        char ch = e.getKeyChar();   //准备附加到输入框的字符
        // 限制不能输入非数字和小数点
        if(!(ch >= '0' && ch <= '9') && ch != '.') {
            e.consume();    // 销毁当前输入字符
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

}
