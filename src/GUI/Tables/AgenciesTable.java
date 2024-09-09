package GUI.Tables;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import Clases.Agencia;

public class AgenciesTable extends BaseTable<Agencia> {
	
	public AgenciesTable(List<Agencia> dataList, String[] columnNames) {
		super(dataList, columnNames);
	}

	@Override
	protected void populateTable(List<Agencia> dataList) {
		
		for(Agencia agencia : dataList) {
			Object[] rowData = {
	                agencia.getIdAgencia(),
	                agencia.getGerente(),
	                agencia.getDireccion()
	                
	            };
	            tableModel.addRow(rowData);
		}
		
	}

	public DefaultTableModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}



	