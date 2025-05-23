package modelo.dao;

import java.util.List;

import modelo.entities.Perfil;

public class PerfilDaoImplJpa extends AbsConexionJpa implements PerfilDao {

	@Override
	public Perfil findById(Integer atributoPk) {
		return em.find(Perfil.class, atributoPk);
	}
	

	@Override
	public int insert(Perfil refEntidad) {
	try {
		tx.begin();
		em.persist(refEntidad);
		tx.commit();
		 filas = 1;
		
	} catch (Exception e) {
		filas = 0;
	}
	return filas;
}

	@Override
	public int update(Perfil atributoPK) {
		try{
			if (findById(atributoPK.getIdPerfil()) != null){
			  tx.begin();
			em.merge(atributoPK);
			tx.commit();
			filas=1;
			} else 
			filas= 0;  
			}catch(Exception e){
			filas = -1;

			}
			return filas;

			}
	
	@Override
	public int deleteById(Integer atributoPk) {
		Perfil juan = findById(atributoPk);
		try {
			if(juan != null) {
				tx.begin();
				em.remove(juan);
				tx.commit();
				filas = 1;
			} else filas = 0;
		}catch(Exception e) {
			filas = -1;
		}
		return filas;
	}

	@Override
	public List<Perfil> findAll() {
		jpql = "from Perfil p";
		query = em.createQuery(jpql);
		return query.getResultList();
	}
	
	

}
