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
            throw new IllegalArgumentException("El ID del cliente debe tener 11 dígitos y solo números.");
        }
        
        // Validar la fecha de nacimiento (primeros seis dígitos)
        String fechaNacimiento = idCliente.substring(0, 6);
        if (!isFechaValida(fechaNacimiento)) {
            throw new IllegalArgumentException("La fecha de nacimiento en el ID no es válida.");
        }

        // Validar género (séptimo dígito)
        char genero = idCliente.charAt(6);
        if (genero != '0' && genero != '1')
        {
            throw new IllegalArgumentException("El género en el ID debe ser 0 (mujer) o 1 (hombre).");
        }
	}

        private boolean isFechaValida(String fecha) {
            // Extraer año, mes y día
            int año = Integer.parseInt(fecha.substring(0, 2));
            int mes = Integer.parseInt(fecha.substring(2, 4));
            int dia = Integer.parseInt(fecha.substring(4, 6));
            
            boolean esValida = true; // Variable para almacenar el resultado

            // Validar mes
            if (mes < 1 || mes > 12) {
                esValida = false;
            } else {
                // Validar día según el mes
                int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if (mes == 2 && (año % 4 == 0) && (año % 100 != 0 || año % 400 == 0)) {
                    diasPorMes[2] = 29; // año bisiesto
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
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }
        this.nombre = nombre;
    }

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
        if (!direccion.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 ,.-]+")) {
            throw new IllegalArgumentException("La dirección solo puede contener letras, números y algunos caracteres especiales.");
        }
        this.direccion = direccion;
    }

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
        if (telefono.length() != 8 || !telefono.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono debe tener 8 dígitos.");
        }
        this.telefono = telefono;
    }

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
	    String gmailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    if (!gmail.matches(gmailRegex)) {
	        throw new IllegalArgumentException("El correo electrónico no es válido.");
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
