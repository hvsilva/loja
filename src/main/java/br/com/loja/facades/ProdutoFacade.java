package br.com.loja.facades;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.loja.entity.Produto;

public class ProdutoFacade implements Serializable {

	private static final long serialVersionUID = 8632270419229128641L;

	// EntityManager entityManager;

	@Inject
	private EntityManager entityManager;

	public void salvarNovoRegistro(Produto produto) {
		try {

			/* INICIANDO UMA TRANSAÇÃO */
			entityManager.getTransaction().begin();

			entityManager.persist(produto);

			/* SE NÃO TIVER ERRO NA OPERAÇÃO ELE EXECUTA O COMMIT */
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			/* SE TIVER ERRO NA OPERAÇÃO É EXECUTADO O rollback */
			entityManager.getTransaction().rollback();
		} finally {

			/*
			 * DEPOIS DE DAR O COMMIT OU ROLLBACK ELE FINALIZA O entityManager
			 */
			entityManager.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Produto> buscaTodos() {
		try {			
			Query query = entityManager.createNamedQuery("Produto.findAll");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Produto> pesquisaNome(Produto produto) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select p.nome from Produto p ");
		sb.append(" where p.nome like :nome ");
		try {
			
			Query q = entityManager.createQuery(sb.toString());
			q.setParameter("nome", produto.getNome());
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
