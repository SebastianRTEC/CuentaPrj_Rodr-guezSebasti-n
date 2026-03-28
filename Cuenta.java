import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta {

    private static int cantCuentasCreadas = 0;

    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;

    public Cuenta(String nombre, double pSaldo) {
        this.nombreCuentaHabiente = nombre;
        this.saldo = pSaldo;
        inicializar();
    }

    public Cuenta(double pSaldo) {
        this.saldo = pSaldo;
        inicializar();
    }

    private void inicializar() {
        cantCuentasCreadas++;
        codCuenta += cantCuentasCreadas;
        fechaCreacion = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public void setNombreCuentaHabiente(String nombre) {
        this.nombreCuentaHabiente = nombre;
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double depositar(double monto) {
        saldo += monto;
        cantDepositosRealizados++;
        return saldo;
    }

    public double retirar(double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados++;
        }
        return saldo;
    }

    private boolean validarRetiro(double monto) {
        return monto <= saldo;
    }

    public static int getCantCuentasCreadas() {
        return cantCuentasCreadas;
    }

    public String toString() {
        return "Cuenta: " + codCuenta +
               "\nNombre: " + nombreCuentaHabiente +
               "\nSaldo: " + saldo +
               "\nFecha: " + fechaCreacion +
               "\nDepositos: " + cantDepositosRealizados +
               "\nRetiros: " + cantRetirosExitososRealizados;
    }
}