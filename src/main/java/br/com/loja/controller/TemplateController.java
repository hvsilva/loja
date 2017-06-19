	package br.com.loja.controller;
	
	import javax.annotation.PostConstruct;
	import javax.enterprise.context.RequestScoped;
	import javax.inject.Named;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	

@Named
@RequestScoped
public class TemplateController{
	
		private static final Logger LOG = LoggerFactory.getLogger(TemplateController.class.getName());
	
		private String hello;
	
		public TemplateController() {
	
			setHello("Hello multichannel team!!!");
		}
	
		@PostConstruct
		public void init() {
			
		}
	
		public void load() {
	
			LOG.trace("preRenderView called");
	
		}
	
		public String getHello() {
			return hello;
		}
	
		public void setHello(String hello) {
			this.hello = hello;
		}
	}