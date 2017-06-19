package br.com.loja.facades;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.loja.entity.Cliente;
import br.com.loja.model.ClienteModel;

public class ClienteFacade implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(Cliente.class.getName());

	private static final long serialVersionUID = -5788507774761758299L;

	@Inject
	private EntityManager entityManager;

	public void salvarNovoRegistro(Cliente cliente) {

		try {

			/* INICIANDO UMA TRANSAÇÃO */
			entityManager.getTransaction().begin();

			entityManager.persist(cliente);

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

	public Cliente validaCliente(ClienteModel clienteModel) {

		try {
			Query query = entityManager.createNamedQuery("Cliente.findByCliente");
			query.setParameter("email", clienteModel.getEmail().trim());
			query.setParameter("senha", clienteModel.getSenha().trim());
			final Cliente cliente = (Cliente) query.getSingleResult();
			LOG.debug("usuario : " + cliente.getNome());
			return cliente;
		} catch (Exception e) {
			LOG.error("erro na consulta", e.getMessage());
			return null;
		}
	}

}
