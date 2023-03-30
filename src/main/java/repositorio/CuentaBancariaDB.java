package repositorio;

import entidades.CuentaBancaria;

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
                    + " tipoDeCuenta INTEGER NOT NULL,\n"
                    + " numeroDeDepositos INTEGER NULL\n"
                    + " numeroDeRetiros INTEGER NULL\n"
                    + " numeroDeTransferencias INTEGER NULL\n"
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
            String sentenciaSql = "INSERT INTO Cuentas (numeroDeCuenta, saldo, propietarioDeLaCuenta, tipoDeCuenta, numeroDeDepositos, numeroDeRetiros, numeroDeTransferencias) " +
                    " VALUES('" + cuentaBancaria.getNumeroDeCuenta() + "', '" + cuentaBancaria.getSaldo()
                    + "', " + cuentaBancaria.getPropietarioDeLaCuenta() + ", '" + cuentaBancaria.getTipoDeCuenta()
                    + "', '" + cuentaBancaria.getNumeroDeDepositos() +  "', '" + cuentaBancaria.getNumeroDeRetiros()
                    + "', '" + cuentaBancaria.getNumeroDeTransferencias() + "');";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Object objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
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
                int numeroDeDepositos = resultadoConsulta.getInt("numeroDeDepositos");
                int numeroDeRetiros = resultadoConsulta.getInt("numeroDeRetiros");
                int numeroDeTransferencias = resultadoConsulta.getInt("numeroDeTransferencias");

                cuentaBancaria = new CuentaBancaria(numeroDeCuentaResultado, saldo, propietarioDeLaCuenta, tipoDeCuenta, numeroDeDepositos, numeroDeRetiros, numeroDeTransferencias);
                return cuentaBancaria;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
        List<CuentaBancaria> personas = new ArrayList<CuentaBancaria>();

        try (Connection conexion = DriverManager.getConnection(cadenaDeConexion)) {
            String sentenciaSql = "SELECT * FROM Cuentas";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    CuentaBancaria cuentaBancaria = null;
                    String numeroDeCuenta = resultadoConsulta.getString("numeroDeCuenta");
                    double saldo = resultadoConsulta.getDouble("saldo");
                    String propietarioDeLaCuenta = resultadoConsulta.getString("propietarioDeLaCuenta");
                    int tipoDeCuenta = resultadoConsulta.getInt("tipoDeCuenta");
                    int numeroDeDepositos = resultadoConsulta.getInt("numeroDeDepositos");
                    int numeroDeRetiros = resultadoConsulta.getInt("numeroDeRetiros");
                    int numeroDeTransferencias = resultadoConsulta.getInt("numeroDeTransferencias");

                    cuentaBancaria = new CuentaBancaria(numeroDeCuenta, saldo, propietarioDeLaCuenta, tipoDeCuenta, numeroDeDepositos, numeroDeRetiros, numeroDeTransferencias);
                    Cuentas.add(cuentaBancaria);
                }
                return Cuentas;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }
}
