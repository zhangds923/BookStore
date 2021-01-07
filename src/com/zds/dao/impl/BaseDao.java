package com.zds.dao.impl;

import com.zds.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 一般是给别人复用代码，所以不需要创建实例，抽象类
 * 就像HttpServlet类一样，一般是别人继承它使用
 */
public abstract class BaseDao {
    //使用Apache-DBUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update方法用来执行insert、update、delete语句
     * @return 如果返回-1 说明执行失败，其他值表示影响的行数
     */
    public int update(String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return -1;
    }

    /**
     * 查询返回一条JavaBean的SQL语句
     * @param type 返回的对象类型
     * @param sql 执行的SQL语句
     * @param args SQL对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 查询返回多个JavaBean的SQL语句
     * @param type 返回的对象类型
     * @param sql 执行的SQL语句
     * @param args SQL对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql,new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

    /**
     * 执行返回一行一列的SQL语句
     * @param sql 执行的SQL
     * @param args SQL对应的参数
     * @return
     */
    public Object queryForSingleValue(String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }
}
