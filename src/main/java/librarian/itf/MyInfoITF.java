package main.java.librarian.itf;

import main.java.dbSQL.UserSql;
import main.java.usermanage.userManage.QueryFrame;
import main.java.usermanage.userManage.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class MyInfoITF extends JFrame {
    private JPanel pan, pan1;
    private JLabel labid, labname, labsex, labyear,labjob;
    private String id,name,sex;
    private int year;
    public MyInfoITF(String id) {
        super("个人信息");
        this.id = id;
        pan = new JPanel(new BorderLayout(5, 5));
        pan1 = new JPanel(new GridLayout(5,1,5,5));
        pan.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(pan);

        try {
            UserSql conSql = new UserSql();
            User user = conSql.queryUser(getId());
            if(user != null) {
                name = user.getName();
                sex = user.getSex();
                year = user.getEntryyear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        labid = new JLabel("工号："+id,JLabel.CENTER);
        labname = new JLabel("姓名："+name,JLabel.CENTER);
        labsex = new JLabel("性别："+sex,JLabel.CENTER);
        labyear = new JLabel("入职年份："+year,JLabel.CENTER);
        labjob = new JLabel("职责：图书管理员",JLabel.CENTER);

        pan1.add(labid);
        pan1.add(labname);
        pan1.add(labsex);
        pan1.add(labyear);
        pan1.add(labjob);

        pan.add(pan1,BorderLayout.CENTER);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
