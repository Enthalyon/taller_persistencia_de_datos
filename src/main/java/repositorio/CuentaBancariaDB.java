package repositorio;

import entidades.CuentaBancaria;
import enums.TipoDeCuenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaBancariaDB implements Repositorio{

    private String cadenaDeConexion;

    public CuentaBancariaDB() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            cadenaDeConexion = "jdbc:sqlite:pruebas.db";
            crearTabla();
        } catch (SQLException e){
            System.out.println("Error de conexión con la base de datos: " + e);
        }
    }

    private void crearTabla() {
        try (Connection conexion = DriverManager.getConnection(cadenaDeConexion)) {

            String sql = "CREATE TABLE IF NOT EXISTS Cuentas (\n"
                    + " idCuenta INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " numeroDeCuenta TEXT NOT NULL UNIQUE,\n"
                    + " saldo FLOAT NULL,\n"
                    + " propietarioDeLaCuenta TEXT NOT NULL ,\n"
                    + " tipoDeCuenta INTEGER NOT NULL, \n"
                    + " numeroRetiros INTEGER NOT NULL, \n"
                    + " numeroTransCuentaAhorro INTEGER NOT NULL, \n"
                    + " numeroTransCuentaCorriente INTEGER NOT NULL, \n"
                    + " numeroDepositos INTEGER NOT NULL \n"
                    + ");";

            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void guardar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaDeConexion)) {
            CuentaBancaria cuentaBancaria = (CuentaBancaria) objeto;
            String sentenciaSql = "INSERT INTO Cuentas (numeroDeCuenta, saldo, propietarioDeLaCuenta, tipoDeCuenta, numeroRetiros, numeroTransCuentaAhorro, numeroTransCuentaCorriente, numeroDepositos) " +
                    " VALUES('" + cuentaBancaria.getNumeroDeCuenta() + "', " + cuentaBancaria.getSaldo()
                    + ", '" + cuentaBancaria.getPropietarioDeLaCuenta() + "', " + cuentaBancaria.getTipoDeCuenta()
                    + ", " + cuentaBancaria.getNumeroDeRetiros() + ", " + cuentaBancaria.getNumeroDeTransferenciasACuentasCorrientes()
                    + ", " + cuentaBancaria.getNumeroDeTransferenciasACuentasDeAhorro() + ", " + cuentaBancaria.getNumeroDeDepositos() +
                    ");";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public void actualizarSaldoYNumeroDeTrasancciones(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaDeConexion)) {
            CuentaBancaria cuentaBancaria = (CuentaBancaria) objeto;
            String sentenciaSql = "UPDATE Cuentas SET saldo = " + cuentaBancaria.getSaldo() + ", " +
                    "numeroRetiros = " + cuentaBancaria.getNumeroDeRetiros() + ", " +
                    "numeroTransCuentaAhorro = " + cuentaBancaria.getNumeroDeTransferenciasACuentasDeAhorro() + ", " +
                    "numeroTransCuentaCorriente = " + cuentaBancaria.getNumeroDeTransferenciasACuentasCorrientes() + ", " +
                    "numeroDepositos = " + cuentaBancaria.getNumeroDeDepositos() + " " +
                    " WHERE numeroDeCuenta = '" + cuentaBancaria.getNumeroDeCuenta() + "';";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public Object buscar(String numeroDeCuenta) {
        try (Connection conexion = DriverManager.getConnection(cadenaDeConexion)) {
            String sentenciaSQL = "SELECT * FROM Cuentas WHERE numeroDeCuenta = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, numeroDeCuenta);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                CuentaBancaria cuentaBancaria = null;
                String numeroDeCuentaResultado = resultadoConsulta.getString("numeroDeCuenta");
                double saldo = resultadoConsulta.getDouble("saldo");
                String propietarioDeLaCuenta = resultadoConsulta.getString("propietarioDeLaCuenta");
                int tipoDeCuenta = resultadoConsulta.getInt("tipoDeCuenta");

                cuentaBancaria = new CuentaBancaria(
                        numeroDeCuentaResultado,
                        saldo,
                        propietarioDeLaCuenta,
                        TipoDeCuenta.values()[tipoDeCuenta]
                );
                return cuentaBancaria;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

}
