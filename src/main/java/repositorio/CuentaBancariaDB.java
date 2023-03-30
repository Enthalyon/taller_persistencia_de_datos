package repositorio;

import entidades.CuentaBancaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

            String sql = "CREATE TABLE IF NOT EXISTS Cuenta (\n"
                    + " idCuenta INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " numeroDeCuenta TEXT NOT NULL,\n"
                    + " saldo FLOAT NOT NULL,\n"
                    + " propietarioDeLaCuenta TEXT NOT NULL ,\n"
                    + " tipoDeCuenta INTEGER NOT NULL UNIQUE,\n"
                    + " numeroDeDepositos INTEGER NOT NULL\n"
                    + " numeroDeRetiros INTEGER NOT NULL\n"
                    + " numeroDeTransferencias INTEGER NOT NULL\n"
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
            String sentenciaSql = "INSERT INTO Cuenta (numeroDeCuenta, saldo, propietarioDeLaCuenta, tipoDeCuenta, numeroDeDepositos, numeroDeRetiros, numeroDeTransferencias) " +
                    " VALUES('" + persona.getNombre() + "', '" + persona.getApellido()
                    + "', " + persona.getEdad() + ", '" + persona.getIdentificacion()
                    + "', '" + persona.getCelular() + "');";
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

    }

    @Override
    public Object buscar(String identificador) {
        return null;
    }

    @Override
    public List<?> listar() {
        return null;
    }
}
