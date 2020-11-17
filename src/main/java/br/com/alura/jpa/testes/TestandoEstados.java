package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class TestandoEstados {
	
	public static void main(String[] args) {
		/* Transient -> Designar o tipo de objeto desvinculado. Sua caracter�stica � uma conta que existe na mem�ria, possui
		 * informa��es e n�o tem "Id" nenhum, mas pode se tornar Managed futuramente*/
		Conta conta = new Conta();
		conta.setTitular("Almiro");
		conta.setAgencia(123123);
		conta.setNumero(12387128);
		
		/* Para fazer esta transforma��o, bastar� termos um "EntityManager" para inserirmos "persist()" passando a "conta"*/
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		// Transient -> Managed
		/* Quando executarmos  a persist�ncia com este m�todo, estaremos trasformando um objeto Transient em Managed, cuja caracter�stica � a sincroniza��o
		 * autom�tica com o banco de dados.*/
		em.persist(conta);
		
		// Managed -> Removed
		/* Caso queiramos remover a conta Managed, poderemos usar o m�todo "remove() passando uma "conta" que ser� deletada de seu contexto JPA, o que
		 * gerar� a sincroniza��o e aplicar� um "delete" no banco de dados, transformando-a em um estado REMOVED*/
		em.remove(conta);
		
		em.getTransaction().commit();
		
		
	}

}
