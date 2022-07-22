/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ProductModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.CategoryModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import Model.OrderDetailModel;

/**
 *
 * @author Admin
 */
public class ProductController {

    public static List<ProductModel> getProduct() {
        List<ProductModel> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select product .*, category.title as category_name from product "
                    + "join category on product.category_id = category.id ";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ProductModel product = new ProductModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("thumbnail"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );

                CategoryModel category = new CategoryModel(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                product.setCategory(category);
                listProduct.add(product);
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

        return listProduct;
    }

    public static void Save(ProductModel productModel) {
        if (productModel.getId() > 0) {
            update(productModel);
        } else {
            insert(productModel);
        }
    }

    private static void insert(ProductModel product) {
        List<ProductModel> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = " insert into product(category_id , name, price, thumbnail, description, quantity, "
                    + " created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?, ?) ";

            statement = conn.prepareStatement(sql);

//            java.util.Date date = new java.util.Date();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String strDate = dateFormat.format(date);
            statement.setInt(1, product.getCategory().getId());
            statement.setString(2, product.getName());
            statement.setFloat(3, product.getPrice());
            statement.setString(4, product.getThumbnail());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getQuantity());
            statement.setString(7, product.getCreated_at());
            statement.setString(8, product.getUpdated_at());

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

    private static void update(ProductModel product) {
        List<ProductModel> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "update product set category_id = ?, name = ?, price = ?, thumbnail = ?, description = ?, quantity = ?, "
                    + " updated_at = ? where id = ? ";
            statement = conn.prepareStatement(sql);

//            java.util.Date date = new java.util.Date();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String strDate = dateFormat.format(date);
            statement.setInt(1, product.getCategory().getId());
            statement.setString(2, product.getName());
            statement.setFloat(3, product.getPrice());
            statement.setString(4, product.getThumbnail());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getQuantity());
            statement.setString(7, product.getUpdated_at());
            statement.setInt(8, product.getId());

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
//        List<ProductModel> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "delete from product where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

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

    public static List<ProductModel> searchByName(String name) {
        List<ProductModel> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select product .*, category.title as category_name from product "
                    + "join category on product.category_id = category.id where name like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ProductModel product = new ProductModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("thumbnail"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                CategoryModel category = new CategoryModel(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );

                product.setCategory(category);
                listProduct.add(product);
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

        return listProduct;
    }

    public static ProductModel findById(int id) {
        ProductModel product = null;
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select product .*, category.title as category_name from product join category on product.category_id = category.id where product.id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                product = new ProductModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("thumbnail"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                CategoryModel category = new CategoryModel(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                product.setCategory(category);
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
        return product;
    }

    public static void updateQuantity(int quantity, int id) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "update product set quantity =  ? where id = ?";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, quantity);
            statement.setInt(2, id);

            statement.executeUpdate();

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

    public static List<ProductModel> getCount(int count) {
        List<ProductModel> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select count(*) from product";
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt(1);
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

        return listProduct;
    }

}
