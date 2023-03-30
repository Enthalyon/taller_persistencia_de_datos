package servicios;

import entidades.CuentaBancaria;
import repositorio.CuentaBancariaDB;
import repositorio.Repositorio;

import java.util.List;

public class ServiciosCuentaBancaria {

    private Repositorio repositorioCuentaBancaria;

    public ServiciosCuentaBancaria() {
        repositorioCuentaBancaria = new CuentaBancariaDB();
    }

    public void guardarCuentaBancaria(CuentaBancaria newPerson) {
        repositorioCuentaBancaria.guardar(newPerson);
    }

    public List<CuentaBancaria> listarCuentasBancarias() {
        return (List<CuentaBancaria>) repositorioCuentaBancaria.listar();
    }

    public CuentaBancaria buscarPersona(String identificador) throws Exception {
        Object cuentaBancaria = repositorioCuentaBancaria.buscar(identificador);
        if(cuentaBancaria == null) {
            throw new Exception("No se encontro la cuenta bancaria");
        }
        return (CuentaBancaria) cuentaBancaria;
    }

}
