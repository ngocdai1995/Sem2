/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.table.DefaultTableModel;
import Model.ProductModel;
import Model.CategoryModel;
import java.util.ArrayList;
import java.util.List;
import Controller.ProductController;
import Controller.CategoryController;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import Model.UsersModel;
import java.text.NumberFormat;
import java.util.Locale;
import Model.ClockThread;
import Utils.Utility;
import Controller.OrderDetailController;
import Model.OrderDetailModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public class ProductJframe extends javax.swing.JFrame {

    DefaultTableModel tableModel;
    List<ProductModel> productList = new ArrayList<>();
    List<CategoryModel> cateList = new ArrayList<>();
    List<UsersModel> userList = new ArrayList<>();
    int position = -1;
    private static String filename = "";
    static ProductModel productModel = null;
    int totalPage, page = 1;
    int limit = 10;
//    List<OrderDetailModel> detailsList;

    /**
     * Creates new form Product
     */
    public ProductJframe() {
        initComponents();
        this.setLocationRelativeTo(null);
        tableModel = (DefaultTableModel) tableProduct.getModel();
        showProduct();
        showCate();

        tableProduct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = tableProduct.getSelectedRow();
//                System.out.println(selectedIndex);
                showRow(selectedIndex);

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        initCLock();
        loadData();
    }

    private void initCLock() {
        ClockThread clock = new ClockThread(lblClock);
        clock.start();
    }

    private void showProduct() {

        productList = ProductController.getProduct();
        tableModel.setRowCount(0);

        for (ProductModel productModel : productList) {
            String price = Utility.getPrice(productModel.getPrice());
            tableModel.addRow(new Object[]{
                tableModel.getRowCount() + 1,
                productModel.getCategory().getName(),
                productModel.getName(),
                price + "đ",
                productModel.getThumbnail(),
                productModel.getQuantity(),
                productModel.getCreated_at(),
                productModel.getUpdated_at()
            });

        }
    }

    private void showCate() {
        cateList = CategoryController.getCategoryList();
        cateList.forEach((categoryModel) -> {
            cbbCate.addItem(categoryModel);
//            System.out.println(categoryModel.getName());
        });
    }

    private void showRow(int selectedIndex) {

        this.position = selectedIndex;
        txtProductName.setText(productList.get(selectedIndex).getName());
        txtQuantity.setText(productList.get(selectedIndex).getQuantity() + "");
        lblThumbnail.setText(productList.get(selectedIndex).getThumbnail());
        txtDescription.setText(productList.get(selectedIndex).getDescription());
        txtPrice.setText(Utility.getPrice(productList.get(selectedIndex).getPrice()));

        int index = 0;
        for (int i = 0; i < cateList.size(); i++) {
            if (cateList.get(i).getId() == productList.get(selectedIndex).getCategory().getId()) {
                index = i;
            }
        }
        cbbCate.setSelectedItem(index);
    }

    public boolean checkValidate() {
        try {
            if (txtProductName.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập ProductName");
                return false;
            }
            if (txtPrice.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập Price");
                return false;
            }
            if (Float.parseFloat(txtPrice.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Price phải lơn hơn hoặc bằng 0");
                return false;
            }

            if (txtQuantity.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập Quantity");
                return false;
            }

            if (Integer.parseInt(txtQuantity.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity phải lơn hơn 0");
                return false;
            }
            if (txtDescription.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập Description");
                return false;
            }
            if (filename == "") {
                JOptionPane.showMessageDialog(this, "Bạn chưa thêm Hình ảnh");
                return false;
            }
        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
        }
        return true;
    }

    public void loadData() {

        double p = (double) productList.size() / limit;
        totalPage = (int) Math.ceil(p);
        int start = (page - 1) * limit;

        productList = ProductController.getProduct();
        tableModel.setRowCount(0);

        for (int i = start; i < start + limit; i++) {
            if (i < productList.size()) {
                tableModel.addRow(new Object[]{
                    i + 1,
                    productList.get(i).getCategory().getName(),
                    productList.get(i).getName(),
                    Utility.getPrice(productList.get(i).getPrice()),
                    productList.get(i).getThumbnail(),
                    productList.get(i).getQuantity(),
                    productList.get(i).getCreated_at(),
                    productList.get(i).getUpdated_at()
                });
            }
        }

        tableProduct.setModel(tableModel);

        lblTotalPage.setText("1/" + totalPage);
        lblPage.setText("1");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton13 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnCash = new javax.swing.JButton();
        btnCate = new javax.swing.JButton();
        btnStaffs = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnSupport = new javax.swing.JButton();
        btnHomes = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        cbbCate = new javax.swing.JComboBox<>();
        btnChoose = new javax.swing.JButton();
        lblThumbnail = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        pn_page = new javax.swing.JPanel();
        btnNhoMax = new javax.swing.JButton();
        btnNho = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();
        btnLon = new javax.swing.JButton();
        btnLonMax = new javax.swing.JButton();
        lblTotalPage = new javax.swing.JLabel();
        lblClock = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jButton13.setText("jButton13");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SANA SUPER MARKET");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NAGERCOIL | KANYAKUMANI DISTRICT - 629 001");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\NGOCDAI\\Desktop\\Hình Ảnh\\anh1112231.png")); // NOI18N

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PRODUCT DETAILS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        btnCash.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cash-32.png"))); // NOI18N
        btnCash.setText("Cash Invoice");
        btnCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashActionPerformed(evt);
            }
        });

        btnCate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/category-32.png"))); // NOI18N
        btnCate.setText("Category");
        btnCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCateActionPerformed(evt);
            }
        });

        btnStaffs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/women-32.png"))); // NOI18N
        btnStaffs.setText("Staffs");
        btnStaffs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStaffsMouseClicked(evt);
            }
        });
        btnStaffs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffsActionPerformed(evt);
            }
        });

        btnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/reports-32.png"))); // NOI18N
        btnReport.setText("Reports");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/log out-32.png"))); // NOI18N
        btnExit.setText("Log Out");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnSupport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/support.png"))); // NOI18N
        btnSupport.setText("Thông tin");
        btnSupport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupportActionPerformed(evt);
            }
        });

        btnHomes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home-32.png"))); // NOI18N
        btnHomes.setText("Home");
        btnHomes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHomes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCash, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStaffs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSupport, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHomes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnCash, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnCate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnStaffs, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSupport, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setText("Product Name");

        jLabel7.setText("Category ");

        jLabel9.setText("Price");

        jLabel10.setText("Quantity");

        jLabel11.setText("Thumbnail");

        txtProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameActionPerformed(evt);
            }
        });

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        jLabel16.setText("Description");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add-24.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update, reset-24.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete-24.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search-32.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnChoose.setText("Chọn File");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Cate_Name", "Name", "Price", "Thumbnail", "Quantity", "Created_At", "Update_At"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableProduct);
        if (tableProduct.getColumnModel().getColumnCount() > 0) {
            tableProduct.getColumnModel().getColumn(6).setResizable(false);
        }

        pn_page.setBackground(new java.awt.Color(204, 204, 204));

        btnNhoMax.setText("<<");
        btnNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhoMaxActionPerformed(evt);
            }
        });
        pn_page.add(btnNhoMax);

        btnNho.setText("<");
        btnNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhoActionPerformed(evt);
            }
        });
        pn_page.add(btnNho);

        lblPage.setText("jLabel5");
        pn_page.add(lblPage);

        btnLon.setText(">");
        btnLon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLonActionPerformed(evt);
            }
        });
        pn_page.add(btnLon);

        btnLonMax.setText(">>");
        btnLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLonMaxActionPerformed(evt);
            }
        });
        pn_page.add(btnLonMax);

        lblTotalPage.setText("jLabel13");
        pn_page.add(lblTotalPage);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbCate, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDescription))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtQuantity))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrice))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(lblThumbnail, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(btnChoose))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)))
                .addContainerGap(94, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(pn_page, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbbCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblThumbnail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChoose))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblClock.setBackground(new java.awt.Color(255, 51, 51));
        lblClock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblClock.setForeground(new java.awt.Color(255, 0, 51));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/clock-32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblClock, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginJframe().setVisible(true);
                setVisible(false);
            }
        });

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupportActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupportJframe().setVisible(true);
                setVisible(false);
            }
        });

    }//GEN-LAST:event_btnSupportActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportJframe().setVisible(true);
                setVisible(false);
            }
        });

    }//GEN-LAST:event_btnReportActionPerformed

    private void btnStaffsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffsActionPerformed
        UsersModel user = LoginJframe.getUser();
        if (user.getRole().getId() == 1) {
            JOptionPane.showMessageDialog(this, "Bạn không phải Admin");
            return;
        } else if (user.getRole().getId() == 2) {
            new StaffsJframe().setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_btnStaffsActionPerformed

    private void btnCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCateActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoryJframe().setVisible(true);
                setVisible(false);
            }
        });

    }//GEN-LAST:event_btnCateActionPerformed

    private void btnCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashInvoiceJframe().setVisible(true);
                setVisible(false);
            }
        });

    }//GEN-LAST:event_btnCashActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (!checkValidate()) {
            return;
        }
        if (!txtPrice.getText().matches("\\d*")) {
            JOptionPane.showMessageDialog(this, "Price nhập chưa đúng định dạng");
            return;
        }
        if (!txtQuantity.getText().matches("\\d*")) {
            JOptionPane.showMessageDialog(this, "Quantity nhập chưa đúng định dạng");
            return;
        }
        CategoryModel categoryModel = (CategoryModel) cbbCate.getSelectedItem();
        String name = txtProductName.getText();
        float price = Float.parseFloat(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String created_at = Utils.Utility.getTimeNow("yyyy-MM-dd HH:mm:ss");
        String updated_at = Utils.Utility.getTimeNow("yyyy-MM-dd HH:mm:ss");
        String description = txtDescription.getText();

        if (position >= 0) {
            productModel = productList.get(position);
            position = -1;
            productModel.setCategory(categoryModel);
            productModel.setName(name);
            productModel.setPrice(price);
            productModel.setThumbnail(filename);
            productModel.setQuantity(quantity);
            productModel.setCreated_at(created_at);
            productModel.setUpdated_at(updated_at);
            productModel.setDescription(description);
            JOptionPane.showMessageDialog(this, "Bạn đã cập nhật thành công");
        } else {
            productModel = new ProductModel(name, price, filename, description, quantity, created_at, updated_at);
            productModel.setCategory(categoryModel);
            JOptionPane.showMessageDialog(this, "Bạn đã thêm mới thành công");
        }

        ProductController.Save(productModel);
        productList = ProductController.getProduct();
        showProduct();
        btnResetActionPerformed(evt);

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files(.txt)", "txt");
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Image Files(.png)", "png");
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Image Files(.jpg)", "jpg");
            FileNameExtensionFilter filter3 = new FileNameExtensionFilter("HTML File(.html)", "html");
            FileNameExtensionFilter filter4 = new FileNameExtensionFilter("PDF File(.pdf)", "pdf");
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.addChoosableFileFilter(filter1);
            fileChooser.addChoosableFileFilter(filter2);
            fileChooser.addChoosableFileFilter(filter3);
            fileChooser.addChoosableFileFilter(filter4);

            fileChooser.setDialogTitle("Save a File");
            int showOpen = fileChooser.showSaveDialog(null);
            if (showOpen == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String imagePath = file.getPath();
                filename = imagePath;
                ImageIcon icon = new ImageIcon(imagePath);

                Image image = icon.getImage().getScaledInstance(lblThumbnail.getWidth(), lblThumbnail.getHeight(), Image.SCALE_SMOOTH);
                lblThumbnail.setIcon(new ImageIcon(image));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ảnh" + e.getMessage());
        }

    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtProductName.setText("");
        txtQuantity.setText("");
//        createdAt_DateChooser.setDate(null);
//        updatedAt_DateChooser.setDate(null);
        txtDescription.setText("");
        txtPrice.setText("");
        lblThumbnail.setText("");

    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        position = tableProduct.getSelectedRow();
        if (position == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm cần xóa !!!");
            return;
        }
        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa sản phẩm này không !!!");
        if (option == 0 && position >= 0) {
            OrderDetailController.delete(productList.get(position).getId());
            ProductController.delete(productList.get(position).getId());
            JOptionPane.showMessageDialog(this, "Bạn đã xóa thành công");
            position = -1;
            ProductController.getProduct();
            showProduct();
            btnResetActionPerformed(evt);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    private void btnStaffsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStaffsMouseClicked
        // TODO add your handling code here:
        UsersModel users = LoginJframe.getUser();
        if (users.getRole().getId() == 1) {
            JOptionPane.showMessageDialog(this, "Bạn không phải Admin");
            return;
        } else if (users.getRole().getId() == 2) {
            new StaffsJframe().setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_btnStaffsMouseClicked

    private void btnHomesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomesActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeJframe().setVisible(true);
                setVisible(false);
            }
        });

    }//GEN-LAST:event_btnHomesActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String fullname = JOptionPane.showInputDialog(this, "Nhập tên bạn muốn tìm kiếm");
        if (fullname != null && fullname.length() > 0) {
            productList = ProductController.searchByName(fullname);
            tableModel.setRowCount(0);

            for (ProductModel productModel : productList) {
                String price = Utility.getPrice(productModel.getPrice());
                tableModel.addRow(new Object[]{
                    tableModel.getRowCount() + 1,
                    productModel.getCategory().getName(),
                    productModel.getName(),
                    price,
                    productModel.getThumbnail(),
                    productModel.getQuantity(),
                    productModel.getCreated_at(),
                    productModel.getUpdated_at()
                });

            }
        } else {
            showProduct();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void btnNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhoActionPerformed
        // TODO add your handling code here:

        if (page > 1) {
            page--;
            loadData();
            lblPage.setText("" + page);
            lblTotalPage.setText(page + "/" + totalPage);
        }
    }//GEN-LAST:event_btnNhoActionPerformed

    private void btnLonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLonActionPerformed
        // TODO add your handling code here:

        if (page < totalPage) {
            page++;
            loadData();
            lblPage.setText("" + page);
            lblTotalPage.setText(page + "/" + totalPage);
        }
    }//GEN-LAST:event_btnLonActionPerformed

    private void btnNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhoMaxActionPerformed
        // TODO add your handling code here:
        page = 1;
        loadData();
        lblPage.setText("1");
        lblTotalPage.setText("1/" + totalPage);
    }//GEN-LAST:event_btnNhoMaxActionPerformed

    private void btnLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLonMaxActionPerformed
        // TODO add your handling code here:
        page = totalPage;
        loadData();
        lblPage.setText("" + page);
        lblTotalPage.setText(page + "/" + totalPage);
    }//GEN-LAST:event_btnLonMaxActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCash;
    private javax.swing.JButton btnCate;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHomes;
    private javax.swing.JButton btnLon;
    private javax.swing.JButton btnLonMax;
    private javax.swing.JButton btnNho;
    private javax.swing.JButton btnNhoMax;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnStaffs;
    private javax.swing.JButton btnSupport;
    private javax.swing.JComboBox<CategoryModel> cbbCate;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblThumbnail;
    private javax.swing.JLabel lblTotalPage;
    private javax.swing.JPanel pn_page;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables

}
