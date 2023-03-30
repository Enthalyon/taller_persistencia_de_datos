package entidades;

import enums.TipoDeCuenta;

public class CuentaBancaria {

    private int idCuenta;

    private String numeroDeCuenta;
    private double saldo = 0;
    private String propietarioDeLaCuenta;
    private TipoDeCuenta tipoDeCuenta; //enum cuentaDeAhorro = 0, cuentaCorriente = 1.
    private int numeroDeDepositos;
    private int numeroDeRetiros;
    private int numeroDeTransferencias;


    public CuentaBancaria(String numeroDeCuenta, double saldo, String propietarioDeLaCuenta, TipoDeCuenta tipoDeCuenta) {

        this.numeroDeCuenta = numeroDeCuenta;
        this.saldo = saldo;
        this.propietarioDeLaCuenta = propietarioDeLaCuenta;
        this.tipoDeCuenta = tipoDeCuenta;
        this.numeroDeDepositos = 0;
        this.numeroDeRetiros = 0;
        this.numeroDeTransferencias = 0;
    }


    public int getIdCuenta() {
        return idCuenta;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getPropietarioDeLaCuenta() {
        return propietarioDeLaCuenta;
    }

    public void setPropietarioDeLaCuenta(String propietarioDeLaCuenta) {
        this.propietarioDeLaCuenta = propietarioDeLaCuenta;
    }

    public TipoDeCuenta getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    public int getNumeroDeDepositos() {
        return numeroDeDepositos;
    }

    public void aumentarDepositos() {
        this.numeroDeDepositos++;
    }

    public int getNumeroDeRetiros() {
        return numeroDeRetiros;
    }

    public void aumentarRetiros() {
        this.numeroDeRetiros++;
    }

    public int getNumeroDeTransferencias() {
        return numeroDeTransferencias;
    }

    public void aumentarTransferencias() {
        this.numeroDeTransferencias++;
    }
}
