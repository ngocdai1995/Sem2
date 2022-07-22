/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.OrderDetailController;
import org.jfree.chart.ChartPanel;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import Model.OrderDetailModel;
import java.awt.Dimension;
import Controller.UsersController;
import Utils.Utility;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGOCDAI
 */
public class Chart {

    static List<UsersModel> usersList;
    static UsersModel staff;

    public static ChartPanel getPieChartPanel(List<OrderDetailModel> detailsList) {
        ChartPanel chartPanel;
        usersList = UsersController.findAll();
        for (UsersModel user : usersList) {
            detailsList = OrderDetailController.getByUser(user);
//            System.out.println(user);
        }
        //tao dataset
        int a = 0, b = 0, c = 0, d = 0;

        for (OrderDetailModel details : detailsList) {
            details.getOrderId();
                      
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("", a);
      
        //tao chart
        JFreeChart chart = ChartFactory.createPieChart(null, dataset, true, true, true);

        //tao panel
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(chartPanel.getWidth(), 321));
        return chartPanel;
    }

    public static ChartPanel getBarChartPanel(List<OrderDetailModel> detailsList) {
        detailsList = OrderDetailController.getAll();
        ChartPanel chartPanel;

        //tao dataset
        int a = 0, b = 0, c = 0, d = 0;

        for (OrderDetailModel details : detailsList) {
            if (details.getOrderId() < 10) {
                a++;
            } else if (details.getOrderId() < 20) {
                b++;
            } else if (details.getOrderId() < 40) {
                c++;
            } else {
                d++;
            }
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(d, "Số SV", "DIST");
        dataset.addValue(c, "Số SV", "CREDIT");
        dataset.addValue(b, "Số SV", "PASS");
        dataset.addValue(a, "Số SV", "FAIL");

        //tạo chart
        JFreeChart barChart = ChartFactory.createBarChart(
                null,
                null, null,
                dataset, PlotOrientation.VERTICAL, false, false, false);

        chartPanel = new ChartPanel(barChart);

        return chartPanel;
    }

}
