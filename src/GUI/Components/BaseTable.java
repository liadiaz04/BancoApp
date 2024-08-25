package GUI.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTable<T> extends JPanel {
	
    protected JTable dataTable;
    protected DefaultTableModel tableModel;
    protected List<T> dataList;

    public BaseTable(List<T> dataList, String[] columnNames) {
        setLayout(new BorderLayout());
        
        // Inicializar el modelo de tabla y la tabla
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        this.dataList = dataList;

        // Definir los nombres de las columnas
        tableModel.setColumnIdentifiers(columnNames);

        // Población del modelo de tabla con datos
        populateTable(dataList);

        // Configurar el panel de desplazamiento y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    

    protected abstract void populateTable(List<T> dataList);
}
