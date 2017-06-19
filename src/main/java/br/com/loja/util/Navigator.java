package br.com.loja.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class Navigator {
	public String login(){	
		return "login?faces-redirect=true";
	}
	
	public String home() {
		return "index?faces-redirect=true";
	}
	
}
