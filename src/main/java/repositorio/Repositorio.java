package repositorio;

import java.util.List;

public interface Repositorio {

    public void guardar(Object objeto); // Crear cuenta

    //public void eliminar(String identificador);

    public void actualizar(Object objeto); // Actualizar cuenta
    public Object buscar(String identificador); // Buscar cuenta por id

    public List<?> listar(); // Busqueda de cuentas general

}
