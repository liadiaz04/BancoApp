package GUI.Tables;

import java.util.List;

import javax.swing.JTable;

import Logic.Cliente;


public class ClientTable extends BaseTable<Cliente> {

	public ClientTable(List<Cliente> dataList, String[] columnNames) {
		super(dataList, columnNames);
	}

	
	
	@Override
	protected void populateTable(List<Cliente> dataList) {
		
		for (Cliente cliente : dataList) {
            Object[] rowData = {
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail()
            };
            tableModel.addRow(rowData);
        }
		
	}
	
	
	
	
}
