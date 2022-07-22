/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.OrderDetailModel;
import Model.UsersModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.OrderModel;
import Model.ProductModel;

/**
 *
 * @author acer
 */
public class OrderDetailController {

    public static List<OrderDetailModel> getAll() {
        List<OrderDetailModel> orderDetailList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select * from order_details "
                    + " join product on product.id = order_details.product_id "
                    + " join orders on orders.id = order_details.order_id ";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderDetailModel orderDetail = new OrderDetailModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("price"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );

                orderDetailList.add(orderDetail);
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
        return orderDetailList;
    }

    public static void insert(OrderDetailModel orderDetail, int Orders_Id) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "insert into order_details(order_id, product_id, quantity, price, created_at, updated_at)"
                    + " values (?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            java.util.Date date = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = dateFormat.format(date);

            statement.setInt(1, Orders_Id);
            statement.setInt(2, orderDetail.getProductId());
            statement.setInt(3, orderDetail.getQuantity());
            statement.setFloat(4, orderDetail.getPrice());
            statement.setString(5, strDate);
            statement.setString(6, strDate);

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

    public static void delete(int id) {
//        List<OrderDetailController> detailsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "delete from order_details where product_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void insertDb(List<OrderDetailModel> details, int Order_Id) {
        for (OrderDetailModel detail : details) {
            insert(detail, Order_Id);
        }
    }

    public static List<OrderDetailModel> getById(int id) {
        List<OrderDetailModel> orderDetailList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select * from order_details left join product on product_id where order_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderDetailModel orderDetail = new OrderDetailModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("price"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                orderDetailList.add(orderDetail);
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
        return orderDetailList;
    }

    public static List<OrderDetailModel> getByUser(UsersModel user) {
        List<OrderDetailModel> orderList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "SELECT * FROM order_details join orders on orders.id = order_details.order_id where orders.staff_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderDetailModel orderDetail = new OrderDetailModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("price"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                              
                orderList.add(orderDetail);
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

    public static List<OrderDetailModel> getByDate(String date) {
        List<OrderDetailModel> orderList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "SELECT * FROM order_details join orders "
                    + "where order_details.created_at between ? and ? ";
            statement = conn.prepareStatement(sql);
            statement.setString(1, date + " 00:00:00");
            statement.setString(2, date + " 23:59:59");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderDetailModel orderDetail = new OrderDetailModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getFloat("price"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                orderList.add(orderDetail);
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

    public static void Update(OrderDetailModel orderDetail) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "update order_details set quantity = ? where product_id = ?";
            statement = conn.prepareStatement(sql);

            java.util.Date date = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String strDate = dateFormat.format(date);

            statement.setInt(1, orderDetail.getQuantity());
            statement.setInt(2, orderDetail.getProductId());

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
}
