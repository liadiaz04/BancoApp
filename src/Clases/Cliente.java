package Clases;

import java.util.ArrayList;


public class Cliente {
	private String idCliente;
	private String nombre;
	private String direccion;
	private String telefono;
	private String gmail;
	private ArrayList <CuentaBancaria> cuentas;
	
	public Cliente(String idCliente, String nombre, String direccion,
			String telefono, String gmail) {
		
		setIdCliente(idCliente);
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(telefono);
		setGmail(gmail);
		this.cuentas = new ArrayList<CuentaBancaria>();
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
        // Validar el formato del carnet de identidad
        if (idCliente.length() != 11 || !idCliente.matches("\\d+")) {
            throw new IllegalArgumentException("El ID del cliente debe tener 11 d�gitos y solo n�meros.");
        }
        
        // Validar la fecha de nacimiento (primeros seis d�gitos)
        String fechaNacimiento = idCliente.substring(0, 6);
        if (!isFechaValida(fechaNacimiento)) {
            throw new IllegalArgumentException("La fecha de nacimiento en el ID no es v�lida.");
        }

        // Validar g�nero (s�ptimo d�gito)
        char genero = idCliente.charAt(6);
        if (genero != '0' && genero != '1')
        {
            throw new IllegalArgumentException("El g�nero en el ID debe ser 0 (mujer) o 1 (hombre).");
        }
	}

        private boolean isFechaValida(String fecha) {
            // Extraer a�o, mes y d�a
            int a�o = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(2, 4));
            int dia = Integer.parseInt(fecha.substring(4, 6));
            
            boolean esValida = true; // Variable para almacenar el resultado

            // Validar mes
            if (mes < 1 || mes > 12) {
                esValida = false;
            } else {
                // Validar d�a seg�n el mes
                int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if (mes == 2 && (a�o % 4 == 0) && (a�o % 100 != 0 || a�o % 400 == 0)) {
                    diasPorMes[2] = 29; // a�o bisiesto
                }

                if (dia < 1 || dia > diasPorMes[mes]) {
                    esValida = false;
                }
            }

            return esValida; // Retornar el resultado final
        }


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
        if (!nombre.matches("[a-zA-Z������������ ]+")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }
        this.nombre = nombre;
    }

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
        if (!direccion.matches("[a-zA-Z������������0-9 ,.-]+")) {
            throw new IllegalArgumentException("La direcci�n solo puede contener letras, n�meros y algunos caracteres especiales.");
        }
        this.direccion = direccion;
    }

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
        if (telefono.length() != 8 || !telefono.matches("\\d+")) {
            throw new IllegalArgumentException("El tel�fono debe tener 8 d�gitos.");
        }
        this.telefono = telefono;
    }

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
	    String gmailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    if (!gmail.matches(gmailRegex)) {
	        throw new IllegalArgumentException("El correo electr�nico no es v�lido.");
	    }
	    this.gmail = gmail;
	}
	
	public ArrayList<String> obtenerSaldosPorCuenta() {
        ArrayList<String> saldos = new ArrayList<String>();
        for (CuentaBancaria cuenta : cuentas) {
            String saldoInfo = "Cuenta ID: " + cuenta.getNoCuenta() + ", Saldo: " + cuenta.getSaldo() + " " + cuenta.getMoneda();
            saldos.add(saldoInfo);
        }
        return saldos;
    }


}
