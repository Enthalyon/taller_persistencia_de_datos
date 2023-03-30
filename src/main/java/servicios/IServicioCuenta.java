package servicios;

import entidades.CuentaBancaria;

public interface IServicioCuenta {
    CuentaBancaria retirar(CuentaBancaria cuenta, double saldoARetirar);
    CuentaBancaria depositar(CuentaBancaria cuenta, double saldoADepositar);
    CuentaBancaria[] transferir(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double cantidadATransferir);
}
