package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriaContaComSaldo {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		
		EntityManager em = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setTitular("Márcia 2");
		conta.setNumero(1236);
		conta.setAgencia(4321);
		conta.setSaldo(100.0);
		
		/*A transação é um escopo de tarefas que serão executadas de uma forma única ou atómica.*/
		em.getTransaction().begin();
		
		em.persist(conta);
		
		//conta.setSaldo(1000.0);
		/* A JPA verificará cada atributo para saber se houve alteração no titular, na agência, número ou saldo. Se houver em algum deles, um "update" será
		 * disparado com a mudança. Este estado Managed também é alcançado quando fazemos um "persist()" além de "find()"*/

		em.getTransaction().commit();
		em.close();
		
		/* Nenhuma atualização acontece depois que fechamos com "em.close()"; todas as contas que antes estavam em estado Managed pelo próprio
		 * "EntityManager" passam a ser DETACHED quando este é fechado.
		 * 
		 * Sua principal característica é NÃO PERMITIR A SINCRONIZAÇÃO AUTOMÁTICA QUANDO HÁ ALTERAÇÃO NO MODELO, e a "Conta" só possui um "Id" porque foi
		 * Managed anteriormente*/
		
		
		/* Nosso objetivo é transformar as entidades para o estado Managed sempre que possível quando trabalhamos com JPA, para justamente podermos usufruir
		 * da sincronização automática*/
		
		/* Então se temos uma Conta que é DETACHED neste momento, precisaremos alterar seu estado para o anterior. Para isso, criaremos mais um 
		 * "EntityManager" após seu fechamento para podermos aplicar "em2.merge()" com a conta, transformando-a em Managed novamente*/
		
		EntityManager em2 = emf.createEntityManager();
		System.out.println("ID da Conta da Márcia -> " + conta.getId());
		conta.setSaldo(500.0);
		
		em2.getTransaction().begin();
		
		em2.merge(conta);
		
		em2.getTransaction().commit();
		
		
		
	}

}
