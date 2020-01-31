package com.lagou.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCDemo1 {

    //jdbc API的三个作用
    //1.与数据库建立连接
    //2.发送sql语句
    //3.返回处理结果
    //这三件事情具体是通过以下类/接口实现的
    //DriverManager 管理jdbc驱动
    //Connection    连接数据库
    //Statement(PreparedStatement)  执行sql语句
    //CallableStatement  调用数据库中的存储过程/存储函数
    //Result   返回的结果集

    //jdbc访问数据库的具体步骤
    //a.导入驱动，加载具体的驱动类
    //b.与数据库建立连接
    //c.发送sql语句，执行sql语句
    //d.处理结果集

    //jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/zdy_mybatisc?characterEncoding=UTF-8";
    private static final String USERNAME="root";
    private static final String PASSWORD="Ytl@2020";

    //数据库的增删改
    public static void update(){
        Connection connection = null;
        Statement statement = null;
        try{
            //a.导入驱动，加载具体的驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //b.与数据库建立连接
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //c.发送sql语句，执行sql语句
            statement = connection.createStatement();
            String sql = "insert into category_ values(2,'Tht')";
            //count表示增删改了几条数据
            //增删改时用executeUpdate
            int count = statement.executeUpdate(sql);
            if(count>0){
                System.out.println("操作成功！");
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(statement!=null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //数据库查询
    public static void query(){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            //a.导入驱动，加载具体的驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //b.与数据库建立连接
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //c.发送sql语句，执行sql语句
            statement = connection.createStatement();
            String sql = "select * from user";
            //查询的时候用executeQuery
            //ResultSet默认是指向数据的前面一行
            rs =statement.executeQuery(sql);
            //ResultSet.next()的作用有2个
            //1.下移
            //2.判断下移之后的元素是否为空
            while(rs.next()){
                //下面这种方式结果一样，1，2代表的是字段在数据库中的顺序
                //rs.getInt("id")
                //rs.getString("name")
                System.out.println("id:"+rs.getInt(1));
                System.out.println("username:"+rs.getString(2));
            }

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs != null) rs.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        query();
    }

    //最后关于Statement与PreparedStatement，两者功能大致一样，但是推荐使用PreparedStatement
    //1.编码更加简便(避免了字符串的拼接)
    //2.提高性能(因为有预编译操作)
    //3.安全(可以有效防止sql注入，原因是因为字符串拼接导致sql语句混为一体)


}