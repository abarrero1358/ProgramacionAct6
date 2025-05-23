package test.dao;

import modelo.dao.PerfilDao;
import modelo.dao.PerfilDaoImplJpa;
import modelo.entities.Perfil;

public class TestPerfilDaoImpl {
	
	private static PerfilDao pDao;
	
	static {
		pDao = new PerfilDaoImplJpa();
	}

	public static void main(String[] args) {
		insertar();
		buscarUno();
		actualizar();
		borrar();
		todos();

	}
    private static void buscarUno() {
		System.out.println("*******************************************");
    	System.out.println("Insertar uno");
    	System.out.println("busco 1 por el 4: " + pDao.findById(4));
    	System.out.println("busco 1 que no existe debe dar null: " + pDao.findById(230));
    	
    }
    
    private static void insertar() {
		System.out.println("*******************************************");
        System.out.println("Inserto uno");
    	
    	Perfil p = new Perfil(5,"hse",120);
    	Perfil p1 = new Perfil(5,"hseq",115);


		System.out.println("*******************************************");
    	System.out.println("inserto uno debe dar 1:" + pDao.insert(p));
    	System.out.println("inserto uno con mismo id debe dar 0: " + pDao.insert(p1));
    }
    
    private static void actualizar() {
		System.out.println("*******************************************");
		System.out.println("Actualizar uno");
    	Perfil p = pDao.findById(5);
    	p.setNombre("cucurucu");
    	
    	System.out.println(" - Actualizo el primero que debe dar 1: " + pDao.update(p));
    	System.out.println(" - le coloque el nombre cucurucu : " + pDao.findById(5));
    }
    
    private static void borrar() {
		System.out.println("*******************************************");
		System.out.println("borrare uno");
    	
    	System.out.println(" - Borro el ultimo que meti debe dar 1: " + pDao.deleteById(5) );
    	System.out.println(" - Borro el mismo otra vez debe dar 0: " + pDao.deleteById(5) );
    }
    private static void todos() {
		System.out.println("*******************************************");
		System.out.println("Todos");
    	
    	pDao.findAll().forEach(ele -> System.out.println(ele));
    
    }
}
