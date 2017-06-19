package br.com.loja.facades;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.loja.entity.Pedido;

public class PedidoFacade implements Serializable {

	private static final long serialVersionUID = -4316061477437435150L;

	@Inject
	private EntityManager entityManager;

	public void salvarNovoRegistro(Pedido pedido) {

		try {

			/* INICIANDO UMA TRANSAÇÃO */
			entityManager.getTransaction().begin();

			entityManager.persist(pedido);

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
}
