package main.java.librarian.itf;

import main.java.librarian.dataClass.Borrow;
import main.java.dbSQL.BorrowSQL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BorrowITF extends JFrame implements ActionListener {
    private JPanel pan, panup;
    private JScrollPane jspan;
    private JTextField tfselect;
    private JComboBox cbreader;
    private JButton btselect;
    private JTable table;
    private DefaultTableModel model;
    private Object[][] borrowData;
    private String[] columns = {"借阅者ID", "借阅者姓名", "图书ISBN号", "图书名称", "借阅时间","经办人"};

    public BorrowITF() {
        super("借阅查询");
        pan = new JPanel(new BorderLayout(5,5));
        pan.setBorder(new EmptyBorder(10,10,10,10));
        setContentPane(pan);
        panup = new JPanel();
        jspan = new JScrollPane(table);
        model = new DefaultTableModel(borrowData,columns);
        table = new JTable(model);
        jspan.setViewportView(table);

        cbreader = new JComboBox();
        cbreader.addItem("借阅者ID");
        cbreader.addItem("借阅者姓名");
        tfselect = new JTextField(20);
        btselect = new JButton("查询");
        btselect.addActionListener(this);

        panup.add(cbreader);
        panup.add(tfselect);
        panup.add(btselect);
        pan.add(panup,BorderLayout.NORTH);
        pan.add(jspan,BorderLayout.CENTER);

        setSize(950,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new BorrowITF();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = (String)cbreader.getSelectedItem();
        String value = tfselect.getText().trim();
        BorrowSQL querysql = new BorrowSQL();

        borrowData = getQuery(querysql.readerQuery(choice, value));
        if (borrowData.length == 0) {
            JOptionPane.showMessageDialog(null, "没有记录");
        } else {
            model = new DefaultTableModel(borrowData, columns);
            table = new JTable(model) {
                @Override
                public boolean isCellEditable(int rowIndex,int ColIndex){
                    return false;
                }
            };
            //居中
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, r);
            jspan.setViewportView(table);
        }

    }

    Object[][] getQuery(List<Borrow> borrowlist) {
        //二维数组返回至表格中
        Object[][] res = new Object[borrowlist.size()][columns.length];
        //数组赋值
        for(int i = 0 ; i < borrowlist.size(); i++){
            Borrow r = borrowlist.get(i);
            res[i][0]=r.getID();
            res[i][1]=r.getReadername();
            res[i][2]=r.getIsbn();
            res[i][3]=r.getBookname();
            res[i][4]=r.getBorDate();
            res[i][5]=r.getStaffName();
        }
        return res;
    }
}
