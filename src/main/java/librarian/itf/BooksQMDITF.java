package main.java.librarian.itf;

import main.java.dbSQL.BooksSQL;
import main.java.librarian.dataClass.Book;
import main.java.librarian.dataClass.BookDel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksQMDITF extends JFrame implements ActionListener {
    private JPanel pan, panup, panbt;
    private JScrollPane jspan;
    private JTextField tfselect;
    private JComboBox cbselect;
    private JButton btselect, btmod, btdel;
    private JTable table;
    private DefaultTableModel model;
    private Object[][] booksData;
    private String[] columns = {"选择", "ISBN号", "名称", "作者", "类别", "出版社", "借出(本)", "馆存(本)","价格(元/本)"};
    private String updisbn = "";
    private List<BookDel> listDel =new ArrayList<>();

    public BooksQMDITF() {
        super("图书信息管理");
        pan = new JPanel(new BorderLayout(5,5));
        pan.setBorder(new EmptyBorder(10,10,10,10));
        setContentPane(pan);
        panup = new JPanel();
        panbt = new JPanel();
        jspan = new JScrollPane(table);
        model = new DefaultTableModel(booksData, columns);
        table = new JTable(model);
        jspan.setViewportView(table);

        cbselect = new JComboBox();
        cbselect.addItem("ISBN号");
        cbselect.addItem("图书名称");
        tfselect = new JTextField(20);
        btselect = new JButton("查询");
        btmod = new JButton("编辑");
        btdel = new JButton("删除");

        btmod.addActionListener(this);
        btdel.addActionListener(this);
        btselect.addActionListener(this);

        panup.add(cbselect);
        panup.add(tfselect);
        panup.add(btselect);
        panbt.add(btmod);
        panbt.add(btdel);
        pan.add(panup,BorderLayout.NORTH);
        pan.add(jspan,BorderLayout.CENTER);
        pan.add(panbt,BorderLayout.SOUTH);

        setSize(950,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new BooksQMDITF();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btmod) {
            int checking = 0, rowIndex = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString());
                if (checked) {
                    checking++;
                    rowIndex = i;
                }
            }
            if (checking == 1) {
                //设置默认值
                updisbn = table.getValueAt(rowIndex, 1).toString();
                System.out.println(updisbn);
                BooksITF upitf = new BooksUpdITF("图书编辑", "总库存(本)：", "修改");
                upitf.tfisbn.setText(updisbn);
                upitf.tfname.setText(table.getValueAt(rowIndex, 2).toString());
                upitf.tfauthor.setText(table.getValueAt(rowIndex, 3).toString());
                int index = Arrays.asList(upitf.Options).indexOf(table.getValueAt(rowIndex, 4).toString());
                if (index != -1) {
                    upitf.cbtype.setSelectedIndex(index);
                }
                else if(upitf.Options.length > 0 ) {
                    upitf.cbtype.setSelectedIndex(0);
                }
                upitf.tfpublisher.setText(table.getValueAt(rowIndex, 5).toString());
                int stock = (Integer)table.getValueAt(rowIndex, 6) + (Integer)table.getValueAt(rowIndex, 7);
                upitf.tfnum.setText(String.valueOf(stock));
                upitf.tfprice.setText(table.getValueAt(rowIndex, 8).toString());
            } else {
                JOptionPane.showMessageDialog(null, "请仅选择一条图书记录");
            }
        }

        if (e.getSource() == btdel) {
            for (int i = 0; i < table.getRowCount(); i++) {
                Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString());
                if (checked) {
                    BookDel bdel = new BookDel(table.getValueAt(i, 1).toString(), i);
                    listDel.add(bdel);
                }
            }
            if (listDel.size() >= 1) {
                new BooksDelITF();
            } else {
                JOptionPane.showMessageDialog(null, "请至少选择一条图书记录");
            }
        }

        if (e.getSource() == btselect) {
            queryTable();
        }
    }
    //返回表格内容list
    Object[][] getSelect(List<Book> booklist) {
        //二维数组返回至表格中
        Object[][] res = new Object[booklist.size()][columns.length];
        //数组赋值
        for(int i = 0 ; i < booklist.size(); i++){
            Book b1 = booklist.get(i);//获取list中的每一个Book
            res[i][0]="";
            res[i][1]=b1.getISBN();
            res[i][2]=b1.getBookName();
            res[i][3]=b1.getAuthor();
            res[i][4]=b1.getType();
            res[i][5]=b1.getPublisher();
            res[i][6]=b1.getBorrow();
            res[i][7]=b1.getSum() - b1.getBorrow();
            res[i][8]=b1.getPrice();
        }
        return res;
    }

    public void queryTable() {
        String choice = (String)cbselect.getSelectedItem();
        String value = tfselect.getText().trim();
        BooksSQL sqlSel = new BooksSQL();
        booksData = getSelect(sqlSel.selectBook(choice, value));
            //表格设置
            model = new DefaultTableModel(booksData, columns) {
                @Override
                public Class<?> getColumnClass(int column) {
                    switch (column){
                        case 0:
                            return Boolean.class;
                        case 1:
                            return String.class;
                        case 2:
                            return String.class;
                        case 3:
                            return String.class;
                        case 4:
                            return String.class;
                        case 5:
                            return String.class;
                        case 6:
                            return String.class;
                        case 7:
                            return String.class;
                        case 8:
                            return String.class;
                    }
                    return super.getColumnClass(column);
                }
            };
            table = new JTable(model) {
                //除第1列外不可编辑
                @Override
                public boolean isCellEditable(int rowIndex,int ColIndex){
                    return ColIndex == 0;
                }
            };
            for (int i = 0; i < booksData.length; i++) {
                model.setValueAt(false, i, 0);
            }
            table.getColumnModel().getColumn(0).setPreferredWidth(5);
            table.getColumnModel().getColumn(6).setPreferredWidth(10);
            table.getColumnModel().getColumn(7).setPreferredWidth(10);
            table.getColumnModel().getColumn(8).setPreferredWidth(15);
            //单元格内容居中
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, r);
            jspan.setViewportView(table);

    }

    public class BooksUpdITF extends BooksITF{
        public BooksUpdITF(String title, String num, String btn) {
            super("图书编辑", "总库存(本)：", "修改");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Book b1 = new Book();
            b1.setISBN(tfisbn.getText().trim());
            b1.setBookName(tfname.getText().trim());
            b1.setAuthor(tfauthor.getText());
            b1.setType((String) cbtype.getSelectedItem());
            b1.setPublisher(tfpublisher.getText());
            b1.setSum(Integer.parseInt(tfnum.getText()));
            b1.setPrice(Double.parseDouble((tfprice.getText())));

            BooksSQL sqlUpd = new BooksSQL();
            sqlUpd.getTypeId(b1);
            int i = sqlUpd.modBook(updisbn, b1);
            System.out.println("ret:"+i);
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "修改成功！");
                dispose();
                queryTable();
            } else {
                JOptionPane.showMessageDialog(null, "修改失败，请重试！");
            }
        }
    }

    public class BooksDelITF extends JFrame implements ActionListener {
        private JPanel pan, pan1, pan2;
        private JLabel labhint;
        private JButton btndel, btcancel;
        public BooksDelITF() {
            super("图书信息删除");
            pan = new JPanel(new BorderLayout(5,5));
            pan1 = new JPanel();
            pan2 = new JPanel();
            pan.setBorder(new EmptyBorder(10,10,10,10));
            setContentPane(pan);

            labhint = new JLabel("是否确认删除？");
            btndel = new JButton("确认");
            btndel.addActionListener(this);
            btcancel = new JButton("取消");
            btcancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            pan1.add(labhint);
            pan2.add(btndel);
            pan2.add(btcancel);
            pan.add(pan1,BorderLayout.CENTER);
            pan.add(pan2, BorderLayout.SOUTH);
            this.setSize(300,150);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setResizable(false);
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btndel) {
                BooksSQL sqlDel = new BooksSQL();
                for (int i = 0; i < listDel.size(); i++) {
                    int k = sqlDel.delBook(listDel.get(i).getIsbn());
                    if (k == 1 && i == listDel.size() - 1) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                        dispose();
                        queryTable();
                    } else if (k != 1) {
                        JOptionPane.showMessageDialog(null, "删除失败");
                        dispose();
                        break;
                    }
                }
            }
        }
    }
}
