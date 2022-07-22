/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.OrderModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class OrderController {

    public static List<OrderModel> getAll() {
        List<OrderModel> orderList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select * from orders";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderModel order = new OrderModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("staff_id"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                orderList.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orderList;
    }

    public static void insert(OrderModel order) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "insert into orders(staff_id, created_at, updated_at)"
                    + " values (?, ?, ?)";
            statement = conn.prepareStatement(sql);

            java.util.Date date = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = dateFormat.format(date);

            statement.setInt(1, order.getStaffId());
            statement.setString(2, strDate);
            statement.setString(3, strDate);

            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static int get() {
        Connection conn = null;
        PreparedStatement statement = null;

        int i = 0;
        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select max(id) from orders";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                i = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return i;
    }

    public static OrderModel getOrderByStaffId_Time(int staff_id, String time) {
        OrderModel order = null;
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select * from orders where staff_id = ? and created_at = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, staff_id);
            statement.setString(2, time);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                order = new OrderModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("staff_id"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
//                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return order;
    }

    public static List<String> getDate() {
        List<String> dateList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select orders.created_at from orders GROUP BY created_at";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String datetime = resultSet.getString("created_at");
                String date = datetime.substring(0, 10);
                if (dateList.contains(date)) {
                } else {
                    dateList.add(date);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dateList;
    }
}
