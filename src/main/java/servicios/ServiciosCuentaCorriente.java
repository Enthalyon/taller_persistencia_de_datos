package servicios;

import entidades.CuentaBancaria;

public class ServiciosCuentaCorriente implements IServicioCuenta {

    @Override
    public CuentaBancaria retirar(CuentaBancaria cuenta, double saldoARetirar) {
        if (saldoARetirar <= cuenta.getSaldo() && cuenta.getNumeroDeRetiros() <= 5) {
            System.out.println("Por favor espere mientras se realiza la transacción");
            cuenta.restarSaldo(saldoARetirar);
            cuenta.aumentarRetiros();
        } else {
            System.out.println("Transacción errada por saldo insuficienteo o limite de retiros superados");
        }
        return cuenta;
    }

    @Override
    public CuentaBancaria depositar(CuentaBancaria cuenta, double saldoADepositar) {
        cuenta.aumentarSaldo(saldoADepositar);
        cuenta.aumentarDepositos();
        return cuenta;
    }

    @Override
    public CuentaBancaria[] transferir(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double cantidadATransferir) {
        double cobroAdicional = 0, cantidadRealARestar = cantidadATransferir;
        cobroAdicional += (cantidadATransferir*2)/100;
        cantidadRealARestar += cobroAdicional;

        if(cuentaOrigen.getTipoDeCuenta() != cuentaDestino.getTipoDeCuenta()) {
            if(cuentaOrigen.getNumeroDeTransferenciasACuentasDeAhorro() < 2) {
                if(cantidadRealARestar > cuentaOrigen.getSaldo()){
                    System.out.println("No se puede realziar la operación, porque la cantida a transferir es mayor al saldo de la cuenta");
                } else {
                    cuentaOrigen.restarSaldo(cantidadRealARestar);
                    cuentaDestino.aumentarSaldo(cantidadATransferir);
                    cuentaOrigen.aumentarNumeroDeTransferenciasACuentasDeAhorro();
                }
            } else {
                System.out.println("Solo puedes hacer dos transferias a una cuenta de ahorros");
            }
        } else {
            if(cantidadRealARestar > cuentaOrigen.getSaldo()){
                System.out.println("No se puede realziar la operación, porque la cantida a transferir es mayor al saldo de la cuenta");
            } else {
                cuentaOrigen.restarSaldo(cantidadRealARestar);
                cuentaDestino.aumentarSaldo(cantidadATransferir);
                cuentaOrigen.aumentarNumeroDeTransferenciasACuentasCorrientes();
            }
        }
        CuentaBancaria[] cuentasBancaria = new CuentaBancaria[]{
                cuentaOrigen, cuentaDestino
        };
        return cuentasBancaria;
    }
}
