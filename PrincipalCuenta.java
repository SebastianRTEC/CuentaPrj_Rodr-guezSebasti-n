import java.util.ArrayList;
import java.util.Scanner;

public class PrincipalCuenta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        Cuenta cuentaActual = null;

        int opcion;

        do {
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar Saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("0) Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1: // Crear cuenta
                    System.out.print("Digite el saldo inicial: ");
                    double saldo = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("¿Desea ingresar también el nombre del cuentahabiente? (s/n): ");
                    String respuesta = sc.nextLine();

                    Cuenta nuevaCuenta;
                    if (respuesta.equalsIgnoreCase("s")) {
                        System.out.print("Digite el nombre del cuentahabiente: ");
                        String nombre = sc.nextLine();
                        nuevaCuenta = new Cuenta(nombre, saldo);
                    } else {
                        nuevaCuenta = new Cuenta(saldo);
                        System.out.print("Digite después el nombre del cuentahabiente: ");
                        String nombre = sc.nextLine();
                        nuevaCuenta.setNombreCuentaHabiente(nombre);
                    }

                    cuentas.add(nuevaCuenta);
                    System.out.println("Cuenta creada exitosamente: " + nuevaCuenta.getCodCuenta());
                    break;

                case 2: // Cantidad de cuentas creadas
                    System.out.println("Cantidad total de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;

                case 3: // Listar cuentas
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas registradas.");
                    } else {
                        for (Cuenta c : cuentas) {
                            System.out.println(c.toString());
                        }
                    }
                    break;

                case 4: // Seleccionar cuenta actual
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas para seleccionar.");
                    } else {
                        System.out.print("Digite el código de la cuenta (ejemplo: cta-1): ");
                        String codigo = sc.nextLine();
                        boolean encontrada = false;
                        for (Cuenta c : cuentas) {
                            if (c.getCodCuenta().equalsIgnoreCase(codigo)) {
                                cuentaActual = c;
                                encontrada = true;
                                System.out.println("Cuenta seleccionada: " + c.toString());
                                break;
                            }
                        }
                        if (!encontrada) {
                            System.out.println("Cuenta no encontrada.");
                        }
                    }
                    break;

                case 5: // Depositar
                    if (cuentaActual == null) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                    } else {
                        System.out.print("Digite el monto a depositar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();
                        double nuevoSaldo = cuentaActual.depositar(monto);
                        System.out.println("Depósito realizado. Nuevo saldo: " + nuevoSaldo);
                    }
                    break;

                case 6: // Retirar
                    if (cuentaActual == null) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                    } else {
                        System.out.print("Digite el monto a retirar: ");
                        double retiro = sc.nextDouble();
                        sc.nextLine();
                        double nuevoSaldo = cuentaActual.retirar(retiro);
                        System.out.println("Operación realizada. Saldo actual: " + nuevoSaldo);
                    }
                    break;

                case 7: // Consultar saldo
                    if (cuentaActual == null) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                    } else {
                        System.out.println("Saldo actual: " + cuentaActual.getSaldo());
                    }
                    break;

                case 8: // Consultar estado completo
                    if (cuentaActual == null) {
                        System.out.println("Debe seleccionar una cuenta primero.");
                    } else {
                        System.out.println("\n=== Estado completo de la cuenta ===");
                        System.out.println(cuentaActual.toString());
                        System.out.println("====================================");
                    }
                    break;

                case 0: // Salir
                    System.out.println("¡Gracias por usar el sistema de cuentas!");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
