/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class ModeloTablaTicket extends DefaultTableModel{
    public ModeloTablaTicket(){
        // Se asignan los nombres de las columnas de la tabla
        // en función de los atributos que tiene la persona
        String[] columnNames = {"ID","FECHA-HORA","IMPORTE"}; 
        
        // Se le indica al modelo el nombre de las columnas y cantidad
        this.setColumnIdentifiers(columnNames); 
    }
    
    public boolean isCellEditable (int row, int column)
    {
        return false;
    }
}
