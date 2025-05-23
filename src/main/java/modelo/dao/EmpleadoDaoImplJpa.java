package modelo.dao;

import java.util.List;
import modelo.entities.Empleado;

public class EmpleadoDaoImplJpa extends AbsConexionJpa implements EmpleadoDao {

	@Override
	public Empleado findById(Integer atributoPk) {
		return em.find(Empleado.class, atributoPk);
	}

	@Override
	public int insert(Empleado refEntidad) {
		try {
			tx.begin();
			em.persist(refEntidad);
			tx.commit();
			filas = 1;
		}catch (Exception e) {
			filas = 0;
		}
		return filas;
	}

	@Override
	public int update(Empleado atributoPK) {
		try {
			if (findById(atributoPK.getIdEmpl()) != null) {
				tx.begin();
				em.merge(atributoPK);
				tx.commit();
				filas = 1;
			}else filas = 0;
		}catch(Exception e) {
			filas = -1;
		}
		return filas;
	}

	@Override
	public int deleteById(Integer atributoPk) {
		Empleado empl = findById(atributoPk);
		try {
			if (empl != null) {
				tx.begin();
				em.remove(empl);
				tx.commit();
				filas = 1;
			}else filas = 0;
		}catch (Exception e)  {
			filas = -1;
		}
		return filas;
	}

	@Override
	public List<Empleado> findAll() {
		jpql = "from Empleado e";
		query =em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<Empleado> empleadoByDepartamento(int idDepar) {
		jpql = "from Empleado e where e.departamento.idDepar = :idDepart" ;
		query = em.createQuery(jpql);
		query.setParameter("idDepart", idDepar);
		return query.getResultList();
	}

	@Override
	public List<Empleado> empleadoByGenero(String sexo) {
		jpql = "from Empleado g where g.genero = :generos";
		query = em.createQuery(jpql);
		query.setParameter("generos", sexo);
		return query.getResultList();
	}

	@Override
	public List<Empleado> empleadoByApellidos(String subcadena) {
		jpql = "from Empleado e where e.apellidos like :sub";
		query = em.createQuery(jpql).setParameter("sub", "%" + subcadena + "%");

		return query.getResultList();
	}
	@Override
	public double salarioTotal() {
		jpql = "select sum(e.salario) from Empleado e";
		query = em.createQuery(jpql);
        Double res = (Double) query.getSingleResult();

		return (res == null) ? 0.0 : res;
	}
	@Override
	public double salarioTotal(int idDepar) {
	    jpql = "select sum(e.salario) from Empleado e where e.departamento.idDepar = :id";
	    query = em.createQuery(jpql).setParameter("id", idDepar);
	    Double res = (Double) query.getSingleResult();

	    return (res != null) ? res : 0.0;
	}

	@Override
	public List<Empleado> empleadoByIdPerfil(int idPerfil) {
		jpql = "from Empleado e where e.perfil.idPerfil = :id";
		query = em.createQuery(jpql).setParameter("id", idPerfil);
		return query.getResultList();
	}

}
