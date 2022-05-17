/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import tablemodel.EmployeesByPositionTableModel;
import tablemodel.GarageOccupationTableModel;
import model.Model;
import tablemodel.MostSoldProductsByMonthTableModel;
import tablemodel.SoldProductsTableModel;
import tablemodel.TotalIncomeByMonthTableModel;
import tablemodel.TotalRentedHoursByClientTableModel;
import view.View;

/**
 *
 * @author soto.aitzol
 */
public class Controller implements ActionListener {
    
    private Model model;
    private View view;
    private Graphics gGraphics;
    private ButtonGroup group1;
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        //view.jLabelLogo.setIcon(new ImageIcon("src/logo.png"));
        URL url = getClass().getResource("/src/logo.png");
        view.jLabelLogo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/logo.png")));
        gehituActionListener(this);

        //view.jTextAreaTextualReports.setEditable(false);
        //Textual radio buttons
        group1 = new ButtonGroup();
        group1.add(view.jRadioButtonSoldProducts);
        group1.add(view.jRadioButtonGarageOcuppation);
        group1.add(view.jRadioButtonProductsByMonth);
        group1.add(view.jRadioButtonTotalRentedHours);
        group1.add(view.jRadioButtonEmployeeByPosition);
        group1.add(view.jRadioButtonTotalIncomeByMonth);
        //Graphic radio buttons
        ButtonGroup group2 = new ButtonGroup();
        group2.add(view.jRadioButtonReport1);
        group2.add(view.jRadioButtonReport2);
        group2.add(view.jRadioButtonReport3);
        
    }
    
    private void gehituActionListener(ActionListener listener) {
        //GUIaren konponente guztiei gehitu listenerra
        view.jButtonTextualReports.addActionListener(listener);
        view.jButtonGraphicReports.addActionListener(listener);
        //Dialog textual reports
        view.jRadioButtonSoldProducts.addActionListener(listener);
        view.jRadioButtonGarageOcuppation.addActionListener(listener);
        view.jRadioButtonProductsByMonth.addActionListener(listener);
        view.jRadioButtonTotalRentedHours.addActionListener(listener);
        view.jRadioButtonEmployeeByPosition.addActionListener(listener);
        view.jRadioButtonTotalIncomeByMonth.addActionListener(listener);
        //Graphic reports
        view.jRadioButtonReport1.addActionListener(listener);
        view.jRadioButtonReport2.addActionListener(listener);
        view.jRadioButtonReport3.addActionListener(listener);
        //Combo box
        view.jComboBoxHilabetea.addActionListener(listener);
        view.jComboBoxEmployeePos.addActionListener(listener);
        view.jComboBoxYear.addActionListener(listener);
        //Save button
        view.jButtonSave.addActionListener(listener);
        //Combo box graphic reports
        view.jComboBoxYearGraphics.addActionListener(listener);
    }
    
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        //listenerrak entzun dezakeen eragiketa bakoitzeko. Konponenteek 'actionCommand' propietatea daukate
        switch (actionCommand) {
            case "GRAPHIC REPORTS":
                view.jDialogGraphicReports.setSize(1200, 600);
                view.jDialogGraphicReports.setResizable(false);
                view.jDialogGraphicReports.setVisible(true);
                view.jComboBoxYearGraphics.setEnabled(false);
                gGraphics = view.jDialogGraphicReports.getGraphics();
                break;
            case "TEXTUAL REPORTS":
                view.jRadioButtonSoldProducts.setSelected(true);
                view.jDialogTextualReports.setSize(750, 625);
                view.jDialogTextualReports.setResizable(false);
                view.jDialogTextualReports.setVisible(true);
                view.jComboBoxHilabetea.setEnabled(false);
                view.jComboBoxEmployeePos.setEnabled(false);
                view.jComboBoxYear.setEnabled(false);
                
                group1 = new ButtonGroup();
                group1.add(view.jRadioButtonSoldProducts);
                group1.add(view.jRadioButtonGarageOcuppation);
                group1.add(view.jRadioButtonProductsByMonth);
                group1.add(view.jRadioButtonTotalRentedHours);
                group1.add(view.jRadioButtonEmployeeByPosition);
                group1.add(view.jRadioButtonTotalIncomeByMonth);
                break;
            case "Most sold products":
                view.jTableReports.setModel(new SoldProductsTableModel(Model.mostSoldProductsArray()));
                view.jComboBoxHilabetea.setEnabled(false);
                view.jComboBoxEmployeePos.setEnabled(false);
                view.jComboBoxYear.setEnabled(false);
                //view.jTextAreaTextualReports.setText("Most Sold Products");
                break;
            case "Garage occupation":
                view.jComboBoxHilabetea.setEnabled(true);
                view.jComboBoxEmployeePos.setEnabled(false);
                view.jComboBoxYear.setEnabled(true);
                int year = Integer.parseInt(view.jComboBoxYear.getSelectedItem().toString());
                view.jTableReports.setModel(new GarageOccupationTableModel(Model.garageOccupationArray(view.jComboBoxHilabetea.getSelectedItem().toString(), year)));
                break;
            case "comboBoxChanged":
                if (view.jRadioButtonGarageOcuppation.isSelected()) {
                    year = Integer.parseInt(view.jComboBoxYear.getSelectedItem().toString());
                    view.jTableReports.setModel(new GarageOccupationTableModel(Model.garageOccupationArray(view.jComboBoxHilabetea.getSelectedItem().toString(), year)));
                } else if (view.jRadioButtonProductsByMonth.isSelected()) {
                    view.jTableReports.setModel(new MostSoldProductsByMonthTableModel(Model.mostSoldProductsByMonth(view.jComboBoxHilabetea.getSelectedItem().toString())));
                }
                break;
            case "Most sold products by month":
                view.jComboBoxHilabetea.setEnabled(true);
                view.jComboBoxEmployeePos.setEnabled(false);
                view.jComboBoxYear.setEnabled(false);
                view.jTableReports.setModel(new MostSoldProductsByMonthTableModel(Model.mostSoldProductsByMonth(view.jComboBoxHilabetea.getSelectedItem().toString())));
                break;
            case "Total rented hours by client":
                view.jComboBoxHilabetea.setEnabled(false);
                view.jComboBoxEmployeePos.setEnabled(false);
                view.jComboBoxYear.setEnabled(false);
                view.jTableReports.setModel(new TotalRentedHoursByClientTableModel(Model.totalRentedHoursByClient()));
                break;
            case "Employees by position":
                view.jComboBoxHilabetea.setEnabled(false);
                view.jComboBoxEmployeePos.setEnabled(true);
                view.jComboBoxYear.setEnabled(false);
                view.jTableReports.setModel(new EmployeesByPositionTableModel(Model.employeesByPosition(view.jComboBoxEmployeePos.getSelectedItem().toString())));
                break;
            case "positionComboBox":
                view.jTableReports.setModel(new EmployeesByPositionTableModel(Model.employeesByPosition(view.jComboBoxEmployeePos.getSelectedItem().toString())));
                break;
            case "Total income by month":
                view.jComboBoxHilabetea.setEnabled(false);
                view.jComboBoxEmployeePos.setEnabled(false);
                view.jComboBoxYear.setEnabled(true);
                year = Integer.parseInt(view.jComboBoxYear.getSelectedItem().toString());
                view.jTableReports.setModel(new TotalIncomeByMonthTableModel(Model.incomeFromSelling(year), Model.incomeFromRenting(year), Model.calculateTotalSelling(Model.incomeFromSelling(year)), Model.calculateTotalRenting(Model.incomeFromRenting(year))));
                break;
            case "yearComboBox":
                year = Integer.parseInt(view.jComboBoxYear.getSelectedItem().toString());
                if (view.jRadioButtonTotalIncomeByMonth.isSelected()) {
                    view.jTableReports.setModel(new TotalIncomeByMonthTableModel(Model.incomeFromSelling(year), Model.incomeFromRenting(year), Model.calculateTotalSelling(Model.incomeFromSelling(year)), Model.calculateTotalRenting(Model.incomeFromRenting(year))));
                } else if (view.jRadioButtonGarageOcuppation.isSelected()) {
                    view.jTableReports.setModel(new GarageOccupationTableModel(Model.garageOccupationArray(view.jComboBoxHilabetea.getSelectedItem().toString(), year)));
                }
                
                break;
            case "SAVE":
                String reportName = "";
                
                if (view.jRadioButtonSoldProducts.isSelected()) {
                    reportName = "Most sold products";
                } else if (view.jRadioButtonGarageOcuppation.isSelected()) {
                    reportName = "Garage occupation";
                } else if (view.jRadioButtonProductsByMonth.isSelected()) {
                    reportName = "Most sold products by month";
                } else if (view.jRadioButtonTotalRentedHours.isSelected()) {
                    reportName = "Total rented hours";
                } else if (view.jRadioButtonEmployeeByPosition.isSelected()) {
                    reportName = "Employees by position";
                } else if (view.jRadioButtonTotalIncomeByMonth.isSelected()) {
                    reportName = "Total income by month";
                }
                
                Model.saveReportTxt(view.jTableReports, reportName);
                break;
            case "Total income":
                //Draw report 1
                view.jComboBoxYearGraphics.setEnabled(true);
                year = Integer.parseInt(view.jComboBoxYearGraphics.getSelectedItem().toString());
                model.drawReport1(gGraphics, Model.incomeFromSelling(year), Model.incomeFromRenting(year));
                break;
            case "Garage occupation by month":
                //Draw report 2
                year = Integer.parseInt(view.jComboBoxYearGraphics.getSelectedItem().toString());
                view.jComboBoxYearGraphics.setEnabled(true);
                model.drawReport2(gGraphics, Model.garageOccupationByMonth(year));
                break;
            case "Products graphic":
                //Draw report 3
                view.jComboBoxYearGraphics.setEnabled(false);
                model.drawReport3(gGraphics, Model.mostSoldProductsArray());
                break;
            case "comboBoxYearGraphics":
                year = Integer.parseInt(view.jComboBoxYearGraphics.getSelectedItem().toString());
                if (view.jRadioButtonReport1.isSelected()) {
                    model.drawReport1(gGraphics, Model.incomeFromSelling(year), Model.incomeFromRenting(year));
                } else if (view.jRadioButtonReport2.isSelected()) {
                    model.drawReport2(gGraphics, Model.garageOccupationByMonth(year));
                }
        }
    }
}
