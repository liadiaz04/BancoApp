package GUI.Tables;


import java.util.List;

import Logic.Contrato;


public class ContratoTable extends BaseTable<Contrato> {

	public ContratoTable(List<Contrato> dataList, String[] columnNames) {
		super(dataList, columnNames);
	}

	@Override
	protected void populateTable(List<Contrato> dataList) {
		
		for (Contrato contrato : dataList) {
            Object[] rowData = {
            	contrato.getEntidad(),
            	String.valueOf(contrato.getPeriodoTiempo()),
            	String.valueOf(contrato.getSalario())
            	
            };
            tableModel.addRow(rowData);
        }
		
	}
	
	

}
