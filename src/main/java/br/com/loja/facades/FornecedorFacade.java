package br.com.loja.facades;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.com.loja.controller.LoginController;
import br.com.loja.entity.Fornecedor;
import br.com.loja.util.Uteis;

public class FornecedorFacade {

	@Inject
	LoginController funcionario;

	// EntityManager entityManager;

	@Inject
	private EntityManager entityManager;

	public void salvarNovoRegistro(Fornecedor fornecedor) {

		try {
			fornecedor.setIdFuncionario(funcionario.GetFuncionarioSession().getId());
			
			/* INICIANDO UMA TRANSAÇÃO */
			entityManager.getTransaction().begin();
			
			entityManager.persist(fornecedor);

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
	public List<Fornecedor> buscaTodos() {

		try {
			// entityManager = Uteis.JpaEntityManager();
			Query query = entityManager.createNamedQuery("Fornecedor.findAll");
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}

	}

}
