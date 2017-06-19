package br.com.loja.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Named(value="menuController")
@RequestScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(MenuController.class.getName());

	private String diaDeHoje;
	private String hora;

	private String nomeOperador;
	
	@Inject
	LoginController loginController;



	@PostConstruct
	public void init(){
		LOG.debug("New Instance");
		diaDeHoje = this.getFormattedTime(Calendar.getInstance(), "dd/MM/yyyy");
		hora = this.getFormattedTime(Calendar.getInstance(), "HH:mm");
		this.nomeOperador = obterNomeOperador();
	}
	
	public void redirecionarHome() {
	}



	private String obterNomeOperador() {
		String nome = loginController.GetFuncionarioSession().getNome();
		return nome.split("\\s+")[0];
	}

	public String getDiaDeHoje() {
		return diaDeHoje;
	}

	public void setDiaDeHoje(String diaDeHoje) {
		this.diaDeHoje = diaDeHoje;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	private String getFormattedTime(Calendar calendar, String format) {
		if (calendar == null) {
			return null;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(calendar.getTime());
	}

	public String getNomeOperador() {
		return nomeOperador;
	}

}
