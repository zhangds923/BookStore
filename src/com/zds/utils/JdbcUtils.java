package com.zds.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource druidDataSource = null;
    //初始化数据库连接池
    static {
        try{
            Properties properties = new Properties();
            //读取配置文件
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");//方法一
            /*File file = new File("src/jdbc.properties");
            String absolutePath = file.getAbsolutePath();
            System.out.println(absolutePath);
            FileInputStream fis = new FileInputStream(new File("src/jdbc.properties"));
            Web下路径容易出问题，还是类加载器比较好用
            */

            //从流中加载数据
            properties.load(is);
            //创建数据库连接池
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 用来获取数据库连接池中的连接
     * @return 如果返回null，说明获取连接失败，有值就是获取成功
     */
    public static Connection getConnection(){
        DruidPooledConnection connection = null;
        try{
            //获取连接
            connection = druidDataSource.getConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //返回连接
        return connection;
    }

    /**
     * 用来关闭数据库连接池中的连接
     * @param connection
     */
    public static void close(Connection connection){
        if (connection != null){
            try{
                connection.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
