package main.java.dbSQL;

import java.io.*;
import java.util.Properties;
    public class MySqlImport {
        public static void main(String args[]) throws IOException {
//            FileInputStream fis = new FileInputStream("src/database.properties");
            InputStream fis = MySqlImport.class.getClassLoader().getResourceAsStream("database.properties");
            Properties properties = new Properties();
            properties.load(fis);
            MySqlImport.importSql(properties);
        }
        /**
         * @param properties
         * @throws IOException
         */
        public static void importSql(Properties properties) throws IOException {
            Runtime runtime = Runtime.getRuntime();
            String cmdarray[] = getImportCommand(properties);
            Process process = runtime.exec(cmdarray[0]);
            OutputStream os = process.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os);
            System.out.println(cmdarray[0]);
            System.out.println(cmdarray[1]);
            System.out.println(cmdarray[2]);
            writer.write(cmdarray[1] + "\r\n" + cmdarray[2]);
            writer.flush();
            writer.close();
            os.close();
        }
        /**
         * @param properties
         * @return
         */
        private static String[] getImportCommand(Properties properties) {
            String username = properties.getProperty("jdbc.username");//用户名
            String password = properties.getProperty("jdbc.password");//密码
            String host = properties.getProperty("jdbc.host");//导入的目标数据库所在的主机
            String port = properties.getProperty("jdbc.port");//使用的端口号
            String importDatabaseName = properties.getProperty("jdbc.importDatabaseName");//导入的目标数据库的名称
            String importPath = properties.getProperty("jdbc.importPath");//导入的目标文件所在的位置
            //获取登录命令语句
            String loginCommand = new StringBuffer().append("mysql -u").append(username).append(" -p").append(password).append(" -h").append(host)
                    .append(" -P").append(port).toString();
            //获取切换数据库到目标数据库的命令语句
            String switchCommand = new StringBuffer("use ").append(importDatabaseName).toString();
            //获取导入的命令语句
            String importCommand = new StringBuffer("source ").append(importPath).toString();
            //需要返回的命令语句数组
            String[] commands = new String[] {loginCommand, switchCommand, importCommand};
            return commands;
        }
    }