package main.java.librarian.itf;

import main.java.librarian.dataClass.Book;
import main.java.dbSQL.BooksSQL;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BooksAddITF extends BooksITF {
    JButton btnClear;
    public BooksAddITF() {
        super("图书入库", "总库存(本)：", "添加入库");
        btnClear = new JButton("清空输入");
        pan2.add(btnClear);
        btnClear.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn) {
            Book b1 = new Book();
            b1.setISBN(tfisbn.getText().trim());
            b1.setBookName(tfname.getText().trim());
            b1.setAuthor(tfauthor.getText());
            b1.setType((String) cbtype.getSelectedItem());
            b1.setPublisher(tfpublisher.getText());
            b1.setSum(Integer.parseInt(tfnum.getText()));
            b1.setPrice(Double.parseDouble(tfprice.getText()));


            BooksSQL sqlAdd = new BooksSQL();
            sqlAdd.getTypeId(b1);
            int i = sqlAdd.insertBook(b1);
            System.out.println("i="+i);
            if (i == 1) {
                JOptionPane.showMessageDialog(null, "添加成功！");
            } else {
                JOptionPane.showMessageDialog(null, "该ISBN号图书已在库，请重新添加！");
            }
        }
        //清空内容
        if (e.getSource() == btnClear) {
            tfisbn.setText("");
            tfname.setText("");
            tfauthor.setText("");
            tfpublisher.setText("");
            tfnum.setText("");
            tfprice.setText("");
        }
    }
}
