package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class AlteraSaldoContaEder {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		
		EntityManager em = emf.createEntityManager();
		
		/* Quando fazemos um find no EntityManager, a JPA e o Hibernate buscar�o no banco e criar�o um objeto do tipo "conta" para ser devovido,
		 * representando o registro buscado no database*/
		Conta contaDoEder = em.find(Conta.class, 1L);
		/* Essa conta devonvida mantem a refer�ncia, ent�o a JPA ainda a conhece mesmo ap�s a devolu��o. Sendo assim, costuma-se dizer que esta
		 * entidade "conta" se encontra no estado Managed, ou seja, gerenciado pela JPA*/
		
		em.getTransaction().begin();
		
		contaDoEder.setSaldo(20.0);
		
		/* Portanto, como os dados originais do banco s�o conhecidos, quando fizermos qualquer alter��o dentro dessa entidade e "commitarmos" a trasa��o,
		 * haver� a sincroniza��o autom�tica*/
		em.getTransaction().commit();
	}

}
