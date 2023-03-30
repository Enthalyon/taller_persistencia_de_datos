package repositorio;

import java.util.List;

public interface Repositorio {

    public void guardar(Object objeto); // Crear cuenta
    public void actualizarSaldoYNumeroDeTrasancciones(Object objeto); // Actualizar cuenta
    public Object buscar(String identificador); // Buscar cuenta por id

}
