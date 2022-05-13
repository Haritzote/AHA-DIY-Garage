/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Renting;
import model.Selling;

/**
 *
 * @author soto.aitzol
 */
public class TotalIncomeByMonthTableModel extends AbstractTableModel{
    private final String[] zutabeIzenak = {"MONTH", "RENTING", "SELLING", "TOTAL"};
    private String[] hilabeteak = {"January","February","March","April","May","June","July","August","September","October","November","December","Total"};
    ArrayList<Selling> incomeFromSelling = new ArrayList();
    ArrayList<Renting> incomeFromRenting = new ArrayList();
    private double total = 0;
    private double totalSelling = 0;
    private double totalRenting = 0;
    
    public TotalIncomeByMonthTableModel(ArrayList<Selling> incomeFromSelling,ArrayList<Renting> incomeFromRenting,double totalIncomeSelling,double totalIncomeRenting){
        this.incomeFromSelling = incomeFromSelling;
        this.incomeFromRenting = incomeFromRenting;
        this.totalSelling = totalIncomeSelling;
        this.totalRenting = totalIncomeRenting;
        this.total = totalSelling + totalRenting;
    }
    @Override
    public int getRowCount() {
        return hilabeteak.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return zutabeIzenak[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return zutabeIzenak.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex == 12 && columnIndex == 3){
            return total;
        }else if(rowIndex == 12 && columnIndex == 2){
            return totalSelling;
        }else if(rowIndex == 12 && columnIndex == 1){
            return totalRenting;
        }else if(columnIndex == 0){
            return hilabeteak[rowIndex];
        }else if (columnIndex == 1){
            return incomeFromRenting.get(rowIndex).getPrice();
        }else if (columnIndex == 2){
            return incomeFromSelling.get(rowIndex).getTotalCost();
        }else if (columnIndex == 3){
            return (double)Math.round((incomeFromRenting.get(rowIndex).getPrice() + incomeFromSelling.get(rowIndex).getTotalCost())*100)/100;
        }
        return null;
    }
   
    
}
