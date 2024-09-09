package GUI.Tables;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Cajero;

public class CajeroTable extends BaseTable<Cajero> {

    public CajeroTable(List<Cajero> dataList, String[] columnNames) {
        super(dataList, columnNames);
    }

    @Override
    protected void populateTable(List<Cajero> dataList) {
        for (Cajero cajero : dataList) {
            Object[] rowData = {
                cajero.getIdCajero(),
                cajero.mostrarSaldoTotal()
            };
            tableModel.addRow(rowData);
        }
    }

    public DefaultTableModel getModel() {
        return tableModel; // Devuelve el modelo de tabla
    }
    
}
