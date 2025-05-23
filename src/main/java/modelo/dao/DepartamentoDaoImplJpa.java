package modelo.dao;

import java.util.List;

import modelo.entities.Departamento;

public class DepartamentoDaoImplJpa extends AbsConexionJpa implements DepartamentoDao {

	@Override
	public Departamento findById(Integer atributoPk) {
		return em.find(Departamento.class, atributoPk);
	}

	@Override
	public int insert(Departamento refEntidad) {
		try {
			tx.begin();
			em.persist(refEntidad);
            tx.commit();	
            filas = 1;
		}catch(Exception e){
			filas = 0;
		}
		return filas;
	}

	@Override
	public int update(Departamento atributoPK) {
		try {
			if (findById(atributoPK.getIdDepar()) != null) {
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
		Departamento DP = findById(atributoPk);
		try {
			if (DP != null) {
				tx.begin();
				em.remove(DP);
				tx.commit();
				filas = 1;
			}
		}catch (Exception e){
			filas = -1;
		}
		return filas;
	}

	@Override
	public List<Departamento> findAll() {
		jpql = "from Departamento D";
		query = em.createQuery(jpql);
		return query.getResultList();
	}

}
