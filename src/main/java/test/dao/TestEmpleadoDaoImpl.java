package test.dao;

import java.time.LocalDate;

import modelo.dao.DepartamentoDao;
import modelo.dao.DepartamentoDaoImplJpa;
import modelo.dao.EmpleadoDao;
import modelo.dao.EmpleadoDaoImplJpa;
import modelo.dao.PerfilDao;
import modelo.dao.PerfilDaoImplJpa;
import modelo.entities.Empleado;

public class TestEmpleadoDaoImpl {

	private static EmpleadoDao eDao;
	private static DepartamentoDao dDao;
	private static PerfilDao pDao;
	
	static {
		
		eDao = new EmpleadoDaoImplJpa();
		dDao = new DepartamentoDaoImplJpa();
		pDao = new PerfilDaoImplJpa();
		
	}
	
	public static void main(String[] args) {
		
		uno();
		todos();
		insertar();
		actualizar();
		borrar();
		porDepartamento();
		porGenero();
		porApellido();
		salarioTotal();
		salarioPorDapartamento();
		porPerfil();
		
	}
	
	private static void porPerfil() {
		System.out.println("*******************************************");
		System.out.println("Busco por el perfil");
		eDao.empleadoByIdPerfil(4).stream().forEach(System.out::println);
	}
	
	private static void salarioPorDapartamento() {
		System.out.println("*******************************************");
		System.out.println("Salario total departamento 20, tiene que dar 193000 = "
						+ eDao.salarioTotal(20));
	}
	
	private static void salarioTotal() {
		System.out.println("*******************************************");
		System.out.println("Salario total tiene que dar 328000 = " + eDao.salarioTotal());
	}
	
	private static void porApellido() {
		System.out.println("*******************************************");
		System.out.println("Busco por el apellido ");
		eDao.empleadoByApellidos("o").stream().forEach(System.out::println);
	}
	
	private static void porGenero() {
		System.out.println("*******************************************");
		System.out.println("Busco por el genero H");
		eDao.empleadoByGenero("h").stream().forEach(System.out::println);
		}
	
	private static void porDepartamento() {
		System.out.println("*******************************************");
		System.out.println("Busco por el departamento 10");
		eDao.empleadoByDepartamento(10).stream().forEach(System.out::println);
	}
	
	private static void actualizar() {
		System.out.println("*******************************************");
		System.out.println("Actualizar");
		Empleado empl = eDao.findAll().getLast();
		empl.setNombre("bastian");
		
		System.out.println("Modifico el ultimo que inserte, debe dar 1: " + eDao.update(empl));
		System.out.println("Le puse de nombre bastian : " + eDao.findById(empl.getIdEmpl()));
	}
	
	private static void borrar() {
		System.out.println("*******************************************");
		System.out.println("Borrar");
		int id = eDao.findAll().getLast().getIdEmpl();
		System.out.println("Borro el que acabo de insertar, debe dar 1: " 
					+ eDao.deleteById(id));
		System.out.println("Borro el mismo debe dar 0: " + eDao.deleteById(id));
	}
	
	private static void insertar() {
		System.out.println("*******************************************");
		System.out.println("Insertar");
		Empleado emp = new Empleado(1,
							"barrero",
							"barrero@gmail.com",
							LocalDate.of(1993, 12, 10),
							LocalDate.of(1990, 12, 3),
							"H",
							"andres",
							"4321",
							1200.5,
							dDao.findById(40),
							pDao.findById(1));
		
		System.out.println(" Insertar un nuevo departamento: " + eDao.insert(emp));
	}
	
	private static void uno() {
		System.out.println("*******************************************");
		System.out.println("Buscar Uno");
		System.out.println("Busco uno existente : " + eDao.findById(100));
		System.out.println("Busco uno que no existe debe dar null : " + eDao.findById(9999999));
	}
	
	private static void todos() {
		System.out.println("*******************************************");
		System.out.println("Busco Todos");
		eDao.findAll().stream().forEach(System.out::println);
	}
	

}
