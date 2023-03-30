package servicios;

import entidades.CuentaBancaria;
import enums.TipoDeCuenta;
import repositorio.CuentaBancariaDB;
import repositorio.Repositorio;

import java.util.List;

public class ServiciosCuentaBancaria {

    private Repositorio repositorioCuentaBancaria;
    private IServicioCuenta servicioCuenta;

    public ServiciosCuentaBancaria() {
        repositorioCuentaBancaria = new CuentaBancariaDB();
    }

    public void guardarCuentaBancaria(CuentaBancaria cuenta) {
        repositorioCuentaBancaria.guardar(cuenta);
    }

    public CuentaBancaria buscarCuentaBancaria(String numeroCuenta) {
        return (CuentaBancaria) repositorioCuentaBancaria.buscar(numeroCuenta);
    }

    public void retirar(CuentaBancaria cuenta, double saldoARetirar){
        if(TipoDeCuenta.values()[cuenta.getTipoDeCuenta()].equals(TipoDeCuenta.CUENTADEAHORRO)){
            servicioCuenta = new ServiciosCuentaAhorro();
        } else {
            servicioCuenta = new ServiciosCuentaCorriente();
        }
        cuenta = servicioCuenta.retirar(cuenta, saldoARetirar);

        repositorioCuentaBancaria.actualizarSaldoYNumeroDeTrasancciones(cuenta);
    }

    public void depositar(CuentaBancaria cuenta, double saldoADepositar){
        if(TipoDeCuenta.values()[cuenta.getTipoDeCuenta()].equals(TipoDeCuenta.CUENTADEAHORRO)){
            servicioCuenta = new ServiciosCuentaAhorro();
        } else {
            servicioCuenta = new ServiciosCuentaCorriente();
        }
        cuenta = servicioCuenta.depositar(cuenta, saldoADepositar);
        repositorioCuentaBancaria.actualizarSaldoYNumeroDeTrasancciones(cuenta);
    }

    public void transferir(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double saldoATransferir){
        if(TipoDeCuenta.values()[cuentaOrigen.getTipoDeCuenta()].equals(TipoDeCuenta.CUENTADEAHORRO)){
            servicioCuenta = new ServiciosCuentaAhorro();
        } else {
            servicioCuenta = new ServiciosCuentaCorriente();
        }
        CuentaBancaria cuentas[] = servicioCuenta.transferir(cuentaOrigen, cuentaDestino, saldoATransferir);
        for (CuentaBancaria cuenta: cuentas) {
            repositorioCuentaBancaria.actualizarSaldoYNumeroDeTrasancciones(cuenta);
        }
    }
}
