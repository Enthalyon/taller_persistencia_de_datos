package servicios;

import entidades.CuentaBancaria;

public class ServiciosCuentaAhorro implements IServicioCuenta{
    public CuentaBancaria retirar(CuentaBancaria cuenta, double saldoARetirar){
        if (saldoARetirar < cuenta.getSaldo()) {
            System.out.println("Por favor espere mientras se realiza la transacción");
            if (cuenta.getNumeroDeRetiros() > 3) {
                double retiroMasComision = (saldoARetirar + (saldoARetirar*1)/100);
                if(retiroMasComision < cuenta.getSaldo()){
                    cuenta.restarSaldo(retiroMasComision);
                } else {
                    System.out.println("Transacción errada, saldo insuficiente");
                }
            }else {
                cuenta.restarSaldo(saldoARetirar);
            }
            cuenta.aumentarRetiros();
        }else {
            System.out.println("Transacción errada, saldo insuficiente");
        }
        return cuenta;
    }
    public CuentaBancaria depositar(CuentaBancaria cuenta, double saldoADepositar){
        if (cuenta.getNumeroDeDepositos() <= 2) {
            cuenta.aumentarSaldo(saldoADepositar + (saldoADepositar*0.5)/100);
        }else {
            cuenta.aumentarSaldo(saldoADepositar);
        }
        cuenta.aumentarDepositos();

        return cuenta;
    }
    public CuentaBancaria[] transferir(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double cantidadATransferir){
        double cobroAdicional = 0, cantidadRealARestar = cantidadATransferir;

        if(cuentaOrigen.getTipoDeCuenta() != cuentaDestino.getTipoDeCuenta()){
            cobroAdicional += (cantidadATransferir*1.5)/100;
            cantidadATransferir += cobroAdicional;

            cuentaOrigen.aumentarNumeroDeTransferenciasACuentasCorrientes();
        } else {
            cuentaOrigen.aumentarNumeroDeTransferenciasACuentasDeAhorro();
        }
        if(cantidadRealARestar > cuentaOrigen.getSaldo()){
            System.out.println("No se puede realziar la operación, porque la cantida a transferir es mayor al saldo de la cuenta");
        } else {
            cuentaOrigen.restarSaldo(cantidadATransferir);
            cuentaDestino.aumentarSaldo(cantidadATransferir);
        }
        CuentaBancaria[] cuentasBancaria = new CuentaBancaria[]{
                cuentaOrigen, cuentaDestino
        };
        return cuentasBancaria;
    }
}
