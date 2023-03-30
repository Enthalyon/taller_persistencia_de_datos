package gui;

import entidades.CuentaBancaria;
import enums.TipoDeCuenta;
import servicios.ServiciosCuentaBancaria;
import java.util.Scanner;

public class GUI_CuentaBancaria {

    private boolean running = true;

    private ServiciosCuentaBancaria serviciosCuentaBancaria;

    public GUI_CuentaBancaria() {
        serviciosCuentaBancaria = new ServiciosCuentaBancaria();
    }

    public void iniciar() {
        System.out.println("Bienvenido al sistema de persistencia de personas");

        while (running) {
            System.out.println("1. Crear una cuenta bancaria");
            System.out.println("2. Buscar una cuenta bancaria");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Transferir");
            System.out.println("6. Salir");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            seleccion(opcion);
        }

    }

    private void seleccion(int seleccion) {
        switch (seleccion) {
            case 1:
                crearcuenta();
                break;
            case 2:
                buscarCuenta();
                break;
            case 3:
                depositar();
                break;
            case 4:
                retirar();
                break;
            case 5:
                transferir();
                break;
            case 6:
                salir();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private void crearcuenta() {
        //String numeroDeCuenta, double saldo, String propietarioDeLaCuenta, TipoDeCuenta tipoDeCuenta
        System.out.println("Crear cuenta bancaria");
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "Ingrese el tipo de cuenta: " +
                        "\n0. Cuenta de ahorros" +
                        "\n1. Cuenta corriente"
        );
        int tipoDeCuentaEntero = scanner.nextInt();
        scanner.nextLine();

        if(tipoDeCuentaEntero == 1 || tipoDeCuentaEntero == 0) {
            TipoDeCuenta tipoDecuenta = TipoDeCuenta.values()[tipoDeCuentaEntero];

            System.out.println("Ingrese el saldo de la cuenta");
            double saldo = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Ingrese el número de la cuenta");
            String numeroCuenta = scanner.nextLine();

            System.out.println("Ingrese el nombre del propietario de la cuenta");
            String propietario = scanner.nextLine();

            serviciosCuentaBancaria.guardarCuentaBancaria(
                    new CuentaBancaria(numeroCuenta, saldo, propietario, tipoDecuenta)
            );

        } else {
            System.out.println("El valor de la cuenta ingresado no es correcto");
        }
    }

    private CuentaBancaria buscarCuenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el numero de la cuenta: ");
        String numeroCuenta = scanner.nextLine();

        try {
            CuentaBancaria cuenta = serviciosCuentaBancaria.buscarCuentaBancaria(numeroCuenta);
            if(cuenta != null){
                System.out.println(cuenta.toString());
            } else {
                System.out.println("La cuenta indicada no existe");
            }
            return cuenta;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al consultar la cuenta indicada");
        }
        return null;
    }

    private void depositar() {
        CuentaBancaria cuenta = buscarCuenta();
        if(cuenta != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el saldo a depositar");
            double saldo = scanner.nextDouble();
            scanner.nextLine();
            serviciosCuentaBancaria.depositar(cuenta, saldo);
        }
    }

    private void retirar() {
        CuentaBancaria cuenta = buscarCuenta();
        if(cuenta != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el saldo a retirar");
            double saldo = scanner.nextDouble();
            scanner.nextLine();
            serviciosCuentaBancaria.retirar(cuenta, saldo);
        }
    }

    private void transferir() {
        System.out.println("Cuenta Origen a transferir");
        CuentaBancaria cuentaOrigen = buscarCuenta();
        System.out.println("Cuenta Destino a transferir");
        CuentaBancaria cuentaDestino = buscarCuenta();
        if(cuentaOrigen != null && cuentaDestino != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el saldo a depositar");
            double saldo = scanner.nextDouble();
            scanner.nextLine();
            serviciosCuentaBancaria.transferir(cuentaOrigen, cuentaDestino, saldo);
        }
    }

    private void salir() {
        System.out.println("Salir");
        running = false;
    }

}
