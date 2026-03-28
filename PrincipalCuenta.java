import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCuenta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int actual = -1; // índice de la cuenta seleccionada

        System.out.println("======================================");
        System.out.println("   CLI de Prueba - Clase Cuenta");
        System.out.println("======================================");

        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú principal");
            System.out.println("1) Crear cuenta");
            System.out.println("2) Conocer cantidad de cuentas creadas");
            System.out.println("3) Listar cuentas");
            System.out.println("4) Seleccionar cuenta actual");
            System.out.println("5) Asignar nombre del cuenta habiente");
            System.out.println("6) Depositar");
            System.out.println("7) Retirar");
            System.out.println("8) Consultar saldo");
            System.out.println("9) Consultar estado de la cuenta");
            System.out.println("0) Salir");
            System.out.print("Opción: ");

            String op = sc.nextLine().trim();

            switch (op) {
                case "1": { // Crear cuenta
                    System.out.println("Tipo de creación:");
                    System.out.println("1) Constructor con un parámetro");
                    System.out.println("2) Constructor con dos parámetros");
                    System.out.print("Elija: ");
                    String tipo = sc.nextLine().trim();

                    System.out.print("Saldo inicial: ");
                    String saldoTexto = sc.nextLine().trim();
                    double saldoInicial;

                    try {
                        saldoInicial = Double.parseDouble(saldoTexto);
                    } catch (NumberFormatException e) {
                        System.out.println("Saldo inválido. No se creó la cuenta.");
                        break;
                    }

                    if (tipo.equals("1")) {
                        Cuenta c = new Cuenta(saldoInicial);
                        cuentas.add(c);
                        actual = cuentas.size() - 1;
                        System.out.println("Cuenta creada y seleccionada (índice " + actual + ").");
                    } else if (tipo.equals("2")) {
                        System.out.print("Nombre del cuenta habiente: ");
                        String nombre = sc.nextLine().trim();
                        Cuenta c = new Cuenta(nombre, saldoInicial);
                        cuentas.add(c);
                        actual = cuentas.size() - 1;
                        System.out.println("Cuenta creada y seleccionada (índice " + actual + ").");
                    } else {
                        System.out.println("Tipo de constructor inválido.");
                    }
                    break;
                }

                case "2": { // Cantidad de cuentas
                    System.out.println("Cantidad total de cuentas creadas: "
                            + Cuenta.getCantCuentasCreadas());
                    break;
                }

                case "3": { // Listar cuentas
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas.");
                    } else {
                        System.out.println("Índice | Código | Saldo");
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    | %s | %.2f%n",
                                    i,
                                    c.getCodCuenta(),
                                    c.getSaldo());
                        }
                    }
                    break;
                }

                case "4": { // Seleccionar cuenta actual
                    if (cuentas.isEmpty()) {
                        System.out.println("Cree una cuenta primero.");
                        break;
                    }

                    System.out.print("Índice de la cuenta a seleccionar: ");
                    String idxTexto = sc.nextLine().trim();

                    try {
                        int idx = Integer.parseInt(idxTexto);
                        if (idx >= 0 && idx < cuentas.size()) {
                            actual = idx;
                            System.out.println("Cuenta índice " + actual + " seleccionada.");
                        } else {
                            System.out.println("Índice fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                    }
                    break;
                }

                case "5": { // Asignar nombre
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }

                    System.out.print("Nombre del cuenta habiente: ");
                    String nombre = sc.nextLine().trim();
                    cuentas.get(actual).setNombreCuentaHabiente(nombre);
                    System.out.println("Nombre asignado correctamente.");
                    break;
                }

                case "6": { // Depositar
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }

                    System.out.print("Monto a depositar: ");
                    String montoTexto = sc.nextLine().trim();

                    try {
                        double monto = Double.parseDouble(montoTexto);
                        double saldoActualizado = cuentas.get(actual).depositar(monto);
                        System.out.printf("Depósito realizado. Nuevo saldo: %.2f%n", saldoActualizado);
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                    }
                    break;
                }

                case "7": { // Retirar
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }

                    System.out.print("Monto a retirar: ");
                    String montoTexto = sc.nextLine().trim();

                    try {
                        double monto = Double.parseDouble(montoTexto);
                        double saldoActualizado = cuentas.get(actual).retirar(monto);
                        System.out.printf("Operación realizada. Saldo actual: %.2f%n", saldoActualizado);
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                    }
                    break;
                }

                case "8": { // Consultar saldo
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }

                    System.out.printf("Saldo actual: %.2f%n", cuentas.get(actual).getSaldo());
                    break;
                }

                case "9": { // Consultar estado
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }

                    System.out.println(cuentas.get(actual).toString());
                    break;
                }

                case "0": {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                }

                default:
                    System.out.println("Opción inválida.");
            }
        }

        sc.close();
    }
}