package br.com.loja.facades;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.loja.entity.AbstractEntity;

public abstract class AbstractPersistence<T extends AbstractEntity, PK extends Number> {

	private Class<T> entityClass;

	public AbstractPersistence(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManagerFactory getEntityManager();

	public T save(T e) {
		if (e.getId() != 0)
			return getEntityManager().createEntityManager().merge(e);
		else {
			getEntityManager().createEntityManager().persist(e);
			return e;
		}
	}

	public void remove(T entity) {
		getEntityManager().createEntityManager().remove(getEntityManager().createEntityManager().merge(entity));
	}

	public T find(PK id) {
		return getEntityManager().createEntityManager().find(entityClass, id);
	}

	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createEntityManager().createQuery(cq).getResultList();
	}

	public List<T> findRange(int[] range) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

}
