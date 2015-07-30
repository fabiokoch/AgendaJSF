package crud;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;

import contatos.Contato;
import util.GerarEntityManager;

@ManagedBean
@SessionScoped
public class ContatoCrud {
	private List<Contato> lista;
	private Contato objeto;
	
	public String incluir(){
		objeto = new Contato();
		return "AgendaForm?faces-redirect=true";
	}
	public String alterar(Integer id) {
		EntityManager em = GerarEntityManager.getInstance().getEntityManager();
		objeto = em.find(Contato.class, id);
		em.close();
		return "AgendaForm?faces-redirect=true";
		}
	
	public String excluir(Integer id) {
		EntityManager em = GerarEntityManager.getInstance().getEntityManager();
		objeto = em.find(Contato.class, id);
		em.getTransaction().begin();
		em.remove(objeto);
		em.getTransaction().commit();
		em.close();
		return "AgendaList?faces-redirect=true";
		}
	
	public String cancelar() {
		return "AgendaList?faces-redirect=true";
		}
	
	public String gravar(){
		EntityManager em = GerarEntityManager.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		em.close();
		return "AgendaList?faces-redirect=true";
		}

	public void inicializarLista() {
		EntityManager em = GerarEntityManager.getInstance().getEntityManager();
		lista = em.createQuery("from Contato order by id").getResultList();
		em.close();
	}

	public ContatoCrud(List<Contato> lista) {
		super();
		this.lista = lista;
	}

	public ContatoCrud() {
		super();
	}

	public List<Contato> getLista() {
		return lista;
	}

	public void setLista(List<Contato> lista) {
		this.lista = lista;
	}

	public ContatoCrud(Contato objeto) {
		super();
		this.objeto = objeto;
	}

	public Contato getObjeto() {
		return objeto;
	}

	public void setObjeto(Contato objeto) {
		this.objeto = objeto;
	}
	

}
