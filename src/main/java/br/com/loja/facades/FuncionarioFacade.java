
package br.com.loja.facades;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.loja.entity.Funcionario;
import br.com.loja.model.FuncionarioModel;

/**
 * @author hvsilva
 *
 */

public class FuncionarioFacade implements Serializable {

	private static final long serialVersionUID = 5125534037915052985L;

	private static final Logger LOG = LoggerFactory.getLogger(Funcionario.class.getName());
	
	@Inject    
    private EntityManager entityManager;

	public Funcionario validaFuncionario(FuncionarioModel funcionarioModel) {

		try {
			//Query query = Uteis.JpaEntityManager().createNamedQuery("Funcionario.findByFuncionario");
			Query query = entityManager.createNamedQuery("Funcionario.findByFuncionario");
			query.setParameter("login", funcionarioModel.getLogin());
			query.setParameter("senha", funcionarioModel.getSenha());

			final Funcionario funcionario = (Funcionario) query.getSingleResult();
			LOG.debug("funcionario : " + funcionario.getNome());
			return funcionario;

		} catch (Exception e) {
			LOG.error("erro na consulta", e.getMessage());
			return null;
		}
	}

}
