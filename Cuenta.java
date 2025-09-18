import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cuenta {
    private String nombreCuentaHabiente; 
    private double saldo;                
    private String codCuenta;            
    private String fechaCreacion;        
    private int cantDepositosRealizados; 
    private int cantRetirosExitosos;   
    private static int contadorCuentas = 0;
    
    public Cuenta(String nombreCuentaHabiente, double pSaldo){
        this.saldo = pSaldo;
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        contadorCuentas++;
        this.codCuenta = "cta-" + contadorCuentas;
        this.fechaCreacion = obtenerFechaActual();
        this.cantDepositosRealizados = 0;
        this.cantRetirosExitosos = 0;
    }
    
    public Cuenta(double pSaldo){
        this.saldo = pSaldo;
        this.nombreCuentaHabiente = "Sin nombre";
        contadorCuentas++;
        this.codCuenta = "cta-" + contadorCuentas;
        this.fechaCreacion = obtenerFechaActual();
        this.cantDepositosRealizados = 0;
        this.cantRetirosExitosos = 0;
    }
    
    // Métodos de instancia
    
    public double retirar (double pMonto){
        if (pMonto > 0 && pMonto >= saldo){
            saldo -= pMonto;
            return saldo;
        }
        return saldo;
    }
    
    public double depositar (double pMonto){
        if (pMonto > 0){
            saldo += pMonto;
            return saldo;
        }
        return saldo;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    // Asignar nombre (para cuando se usa el constructor de 1 parámetro)
    public void setNombreCuentaHabiente(String nombre) {
        this.nombreCuentaHabiente = nombre;
    }

    // Consultar nombre
    public String getNombreCuentaHabiente() {
        return nombreCuentaHabiente;
    }
    
    // Consultar código de cuenta
    public String getCodCuenta() {
        return codCuenta;
    }
    
    // toString: devuelve TODO el estado de la cuenta
    @Override
    public String toString() {
        return "Cuenta: " + codCuenta +
               " Nombre: " + nombreCuentaHabiente +
               " Saldo: " + saldo +
               " Fecha Creación: " + fechaCreacion +
               " Depósitos realizados: " + cantDepositosRealizados +
               " Retiros exitosos: " + cantRetirosExitosos;
    }
    
    // Métodos privados
    private boolean validarRetiro(double monto) {
        return monto > 0 && monto <= saldo;
    }

    private String obtenerFechaActual() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ahora.format(formato);
    }

    // Métodos de clase

    // Consultar la cantidad total de cuentas creadas
    public static int getCantCuentasCreadas() {
        return contadorCuentas;
    }
}
