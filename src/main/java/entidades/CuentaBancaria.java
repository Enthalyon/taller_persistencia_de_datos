package entidades;

import enums.TipoDeCuenta;

public class CuentaBancaria {
    private String numeroDeCuenta;
    private double saldo = 0;
    private String propietarioDeLaCuenta;
    private TipoDeCuenta tipoDeCuenta; //enum cuentaDeAhorro = 0, cuentaCorriente = 1.
    private int numeroDeDepositos;
    private int numeroDeRetiros;
    private int numeroDeTransferenciasACuentasDeAhorro;
    private int numeroDeTransferenciasACuentasCorrientes;

    public CuentaBancaria(String numeroDeCuenta, double saldo, String propietarioDeLaCuenta, TipoDeCuenta tipoDeCuenta) {

        this.numeroDeCuenta = numeroDeCuenta;
        this.saldo = saldo;
        this.propietarioDeLaCuenta = propietarioDeLaCuenta;
        this.tipoDeCuenta = tipoDeCuenta;
        this.numeroDeDepositos = 0;
        this.numeroDeRetiros = 0;
        this.numeroDeTransferenciasACuentasDeAhorro = 0;
        this.numeroDeTransferenciasACuentasCorrientes = 0;
    }

    public void aumentarNumeroDeTransferenciasACuentasDeAhorro() {
         numeroDeTransferenciasACuentasDeAhorro ++;
    }

    public void  aumentarNumeroDeTransferenciasACuentasCorrientes() {
        numeroDeTransferenciasACuentasCorrientes ++;
    }

    public int getNumeroDeTransferenciasACuentasDeAhorro() {
        return numeroDeTransferenciasACuentasDeAhorro;
    }

    public int getNumeroDeTransferenciasACuentasCorrientes() {
        return numeroDeTransferenciasACuentasCorrientes;
    }

    public void restarSaldo(double saldoARestar){
        saldo -= saldoARestar;
    }
    public void aumentarSaldo(double saldoAAumentar){
        saldo += saldoAAumentar;
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

    public int getTipoDeCuenta() {
        return tipoDeCuenta.ordinal();
    }

    @Override
    public String toString(){
        return "Tipo de cuenta: " + tipoDeCuenta + "\n"
                + "Numero de cuenta: " + numeroDeCuenta + "\n"
                + "Propietario de la cuenta: " + propietarioDeLaCuenta + "\n"
                + "Saldo de la cuenta: " + saldo;
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
}
