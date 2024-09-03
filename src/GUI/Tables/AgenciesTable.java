package GUI.Tables;

import java.util.List;

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

}



	