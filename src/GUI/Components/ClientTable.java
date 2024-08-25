package GUI.Components;

import java.util.List;

import Clases.Cliente;

import com.sun.security.ntlm.Client;

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
                cliente.getGmail()
            };
            tableModel.addRow(rowData);
        }
		
	}
	
	

}
