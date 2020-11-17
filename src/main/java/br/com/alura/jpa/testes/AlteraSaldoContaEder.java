package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class AlteraSaldoContaEder {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		
		EntityManager em = emf.createEntityManager();
		
		/* Quando fazemos um find no EntityManager, a JPA e o Hibernate buscarão no banco e criarão um objeto do tipo "conta" para ser devovido,
		 * representando o registro buscado no database*/
		Conta contaDoEder = em.find(Conta.class, 1L);
		/* Essa conta devonvida mantem a referência, então a JPA ainda a conhece mesmo após a devolução. Sendo assim, costuma-se dizer que esta
		 * entidade "conta" se encontra no estado Managed, ou seja, gerenciado pela JPA*/
		
		em.getTransaction().begin();
		
		contaDoEder.setSaldo(20.0);
		
		/* Portanto, como os dados originais do banco são conhecidos, quando fizermos qualquer alterção dentro dessa entidade e "commitarmos" a trasação,
		 * haverá a sincronização automática*/
		em.getTransaction().commit();
	}

}
