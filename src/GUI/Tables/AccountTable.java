package GUI.Tables;

import java.util.List;

import Logic.CuentaBancaria;

public class AccountTable extends BaseTable<CuentaBancaria> {

    public AccountTable(List<CuentaBancaria> dataList, String[] columnNames) {
        super(dataList, columnNames);
    }

    @Override
    protected void populateTable(List<CuentaBancaria> dataList) {
        for (CuentaBancaria cuenta : dataList) {
            Object[] rowData = {
                cuenta.getNoCuenta(),
                String.valueOf(cuenta.getSaldo()),
                cuenta.getBeneficiario(),
                cuenta.getMoneda(),
                String.valueOf(cuenta.getFechaApertura()),
                cuenta.getClass().getSimpleName() // Obtener el tipo de cuenta
            };
            tableModel.addRow(rowData); // A�adir fila a la tabla
        }
    }
}
