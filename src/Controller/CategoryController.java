/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CategoryModel;
import java.sql.Connection;
import java.sql.Date;
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

/**
 *
 * @author Admin
 */
public class CategoryController {

    public static List<CategoryModel> getCategoryList() {
        List<CategoryModel> listCate = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select * from category";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listCate.add(new CategoryModel(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return listCate;
    }

    public static void save(CategoryModel cate) {
        if (cate.getId() > 0) {
            update(cate);
        } else {
            insert(cate);
        }
    }

    private static void insert(CategoryModel cate) {
        List<CategoryModel> listCate = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "insert into category (title, created_at, updated_at) value (?, ?, ?)";
            statement = conn.prepareStatement(sql);

//            java.util.Date date = new java.util.Date();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String strDate = dateFormat.format(date);

            statement.setString(1, cate.getName());
            statement.setString(2, cate.getCreated_at());
            statement.setString(3, cate.getUpdated_at());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private static void update(CategoryModel cate) {
        List<CategoryModel> listCate = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "update category set title = ?, updated_at = ? where id = ?";
            statement = conn.prepareStatement(sql);
            
//            java.util.Date date = new java.util.Date();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String strDate = dateFormat.format(date);
            
            statement.setString(1, cate.getName());
            statement.setString(2, cate.getUpdated_at());
            statement.setInt(3, cate.getId());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public static void delete(int id) {
        List<CategoryModel> listCate = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "delete from category where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public static List<CategoryModel> searchByName(String name) {
        List<CategoryModel> listCate = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select * from category where title like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listCate.add(new CategoryModel(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listCate;
        }
    }
    
    public static long convertDateToDate(Date date) {
        return date.getTime();
    }
}
