package br.com.loja.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.loja.controller.LoginController;
import br.com.loja.entity.Cliente;

@Named(value = "verificaLogado")
@RequestScoped
public class VerificaLogado {
	
	@Inject
	private LoginController loginController;


	public boolean estaLogado() {


		Cliente c = loginController.GetClienteSession();

		if (c != null) {
			return true;
		}

		return false;
	}
}
