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


        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row)) {
                    c.setBackground(new Color(144, 238, 144)); 
                } else {
                    c.setBackground(Color.WHITE); 
                }
                if (column == 0) {
                    c.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
                }
                return c;
            }
        };


        dataTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dataTable.setRowHeight(25); 


        tableModel.setColumnIdentifiers(columnNames);


        populateTable(dataList);


        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
        

        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = dataTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(100); // Ajustar el ancho preferido
        }


        JTableHeader header = dataTable.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 18));
        header.setBackground(new Color(119, 221, 119));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40)); 
    }
    

    public JTable getTable() {
        return dataTable; 
    }

    protected abstract void populateTable(List<T> dataList);
}
