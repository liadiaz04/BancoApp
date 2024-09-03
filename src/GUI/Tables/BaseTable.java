package GUI.Tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.List;

public abstract class BaseTable<T> extends JPanel {
    
    protected JTable dataTable;
    protected DefaultTableModel tableModel;
    protected List<T> dataList;

    public BaseTable(List<T> dataList, String[] columnNames) {
        setLayout(new BorderLayout());

        // Inicializar el modelo de tabla y la tabla
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row)) {
                    c.setBackground(new Color(144, 238, 144)); // Color de fondo para fila seleccionada
                } else {
                    c.setBackground(Color.WHITE); // Color de fondo para filas no seleccionadas
                }
                if (column == 0) {
                    c.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Cambiar la fuente de la primera columna a normal
                }
                return c;
            }
        };

        // Cambiar la fuente de la tabla
        dataTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dataTable.setRowHeight(25); // Ajustar la altura de las filas

        // Definir los nombres de las columnas
        tableModel.setColumnIdentifiers(columnNames);

        // Población del modelo de tabla con datos
        populateTable(dataList);

        // Configurar el panel de desplazamiento y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Ajustar el tamaño de las columnas automáticamente
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = dataTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(100); // Ajustar el ancho preferido
        }

        // Personalizar el encabezado de la tabla
        JTableHeader header = dataTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 18));
        header.setBackground(new Color(119, 221, 119));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40)); // Ajustar la altura del encabezado
    }
    
	// Método para obtener la JTable
    public JTable getTable() {
        return dataTable; // Asegúrate de que 'table' sea un campo de tipo JTable en BaseTable
    }

    protected abstract void populateTable(List<T> dataList);
}
