/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UsersModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.UsersModel;
import Model.RoleModel;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import EndCode.Md5;

/**
 *
 * @author Admin
 */
public class UsersController {

    private static Md5 md5;
    private static Connection conn;
    private static PreparedStatement statement;

    public static List<UsersModel> findAll() {
        List<UsersModel> userList = new ArrayList<>();
        conn = null;
        statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select users .*, role.name as role_name from users "
                    + " join role on users.role_id = role.id";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UsersModel users = new UsersModel(
                        resultSet.getInt("id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("password"),
                        resultSet.getString("birth_day"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at"),
                        resultSet.getString("gender")
                );
                RoleModel role = new RoleModel(
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name")
                );
                users.setRole(role);
                userList.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return userList;
    }

    public static void save(UsersModel user) {
        if (user.getId() > 0) {
            update(user);
        } else {
            insert(user);
        }
    }

    public static boolean insert(UsersModel user) {
        List<UsersModel> userList = new ArrayList<>();
        conn = null;
        statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "insert into users (fullname, email, phone_number, address, password, birth_day, role_id, created_at, updated_at, gender) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            java.util.Date date = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = dateFormat.format(date);

            statement.setString(1, user.getFullname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getBirthday());
            statement.setInt(7, user.getRole().getId());
            statement.setString(8, strDate);
            statement.setString(9, strDate);
            statement.setString(10, user.getGender());

            statement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public static void update(UsersModel user) {
        List<UsersModel> userList = new ArrayList<>();
        conn = null;
        statement = null;
//        Md5 md5 = new Md5(user.getPassword());
//        String passMd5 = md5.getMd5();
        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "update users set fullname = ?, phone_number = ?, address = ?,  email = ?, password = ?, birth_day = ?, gender = ?, updated_at = ?, role_id = ? where id = ?";
            statement = conn.prepareStatement(sql);

            java.util.Date date = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = dateFormat.format(date);

            statement.setString(1, user.getFullname());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getBirthday());
            statement.setString(7, user.getGender());
            statement.setString(8, strDate);
            statement.setInt(9, user.getRole().getId());
            statement.setInt(10, user.getId());

            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void delete(int id) {
        List<UsersModel> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "delete from users where id = ?";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static List<UsersModel> searchByName(String name) {
        List<UsersModel> userList = new ArrayList<>();
        conn = null;
        statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select users .*, role.name as role_name from users "
                    + " join role on users.role_id = role.id where fullname like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UsersModel users = new UsersModel(
                        resultSet.getInt("id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("birth_day"),
                        resultSet.getString("gender"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                RoleModel role = new RoleModel(
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name")
                );
                users.setRole(role);
                userList.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return userList;
    }

    public static UsersModel getLogin(String email, String password) {
        UsersModel users = null;
        conn = null;
        statement = null;
        Md5 md5 = new Md5(password);
        String passMd5 = md5.getMd5();
        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select users .*, role.name as role_name from users "
                    + " join role on users.role_id = role.id where email = ? and password = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, passMd5);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users = new UsersModel(
                        resultSet.getInt("id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("birth_day"),
                        resultSet.getString("gender"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                RoleModel role = new RoleModel(
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name")
                );
                users.setRole(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return users;
    }

    public static boolean login(String email, String password) throws SQLException {
        conn = null;
        statement = null;
        Md5 md5 = new Md5(password);
        String passMd5 = md5.getMd5();

        conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
        String sql = "select users .*, role.name as role_name from users "
                + " join role on users.role_id = role.id where email = ? and password = ?";
        statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, passMd5);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            if (email.equals(resultSet.getString(5)) && passMd5.equals(resultSet.getString(6))) {
                return true;
            }
        }

        return false;
    }

    public static List<RoleModel> findAllRole() {
        List<RoleModel> roleList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select * from role";
            statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RoleModel role = new RoleModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                roleList.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return roleList;
    }

    public static List<UsersModel> findByID(int id) {
        List<UsersModel> userList = new ArrayList<>();
        conn = null;
        statement = null;

        try {
            conn = DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
            String sql = "select users .*, role.name as role_name from users "
                    + " join role on users.role_id = role.id where fullname like ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UsersModel users = new UsersModel(
                        resultSet.getInt("id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("birth_day"),
                        resultSet.getString("gender"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at")
                );
                RoleModel role = new RoleModel(
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name")
                );
                users.setRole(role);
                userList.add(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return userList;
    }
}
