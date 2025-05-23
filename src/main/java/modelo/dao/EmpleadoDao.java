package modelo.dao;


import java.util.List;
import modelo.entities.Empleado;

public interface EmpleadoDao extends ICrudGenerico<Empleado,Integer> {
	 List<Empleado> empleadoByDepartamento (int idDepar);
	 List<Empleado> empleadoByGenero (String sexo);
	 List<Empleado> empleadoByApellidos (String subcadena);
	 double salarioTotal();
	 double salarioTotal(int idDepar);
	 List<Empleado> empleadoByIdPerfil(int idPerfil);
	
	 
	
}
