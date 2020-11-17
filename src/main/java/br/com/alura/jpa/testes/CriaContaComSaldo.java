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
		conta.setTitular("M�rcia 2");
		conta.setNumero(1236);
		conta.setAgencia(4321);
		conta.setSaldo(100.0);
		
		/*A transa��o � um escopo de tarefas que ser�o executadas de uma forma �nica ou at�mica.*/
		em.getTransaction().begin();
		
		em.persist(conta);
		
		//conta.setSaldo(1000.0);
		/* A JPA verificar� cada atributo para saber se houve altera��o no titular, na ag�ncia, n�mero ou saldo. Se houver em algum deles, um "update" ser�
		 * disparado com a mudan�a. Este estado Managed tamb�m � alcan�ado quando fazemos um "persist()" al�m de "find()"*/

		em.getTransaction().commit();
		em.close();
		
		/* Nenhuma atualiza��o acontece depois que fechamos com "em.close()"; todas as contas que antes estavam em estado Managed pelo pr�prio
		 * "EntityManager" passam a ser DETACHED quando este � fechado.
		 * 
		 * Sua principal caracter�stica � N�O PERMITIR A SINCRONIZA��O AUTOM�TICA QUANDO H� ALTERA��O NO MODELO, e a "Conta" s� possui um "Id" porque foi
		 * Managed anteriormente*/
		
		
		/* Nosso objetivo � transformar as entidades para o estado Managed sempre que poss�vel quando trabalhamos com JPA, para justamente podermos usufruir
		 * da sincroniza��o autom�tica*/
		
		/* Ent�o se temos uma Conta que � DETACHED neste momento, precisaremos alterar seu estado para o anterior. Para isso, criaremos mais um 
		 * "EntityManager" ap�s seu fechamento para podermos aplicar "em2.merge()" com a conta, transformando-a em Managed novamente*/
		
		EntityManager em2 = emf.createEntityManager();
		System.out.println("ID da Conta da M�rcia -> " + conta.getId());
		conta.setSaldo(500.0);
		
		em2.getTransaction().begin();
		
		em2.merge(conta);
		
		em2.getTransaction().commit();
		
		
		
	}

}
