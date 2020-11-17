package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;

public class TesteJPQL {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		// String query = "select * from movimentacao where conta_id = 2";
		
		/* Quando trabalhamos com JPA, h� uma linguagem Chamada "Java Persistence Language" ou JPQL, a qual � uma "query" de mais alto n�vel que nos
		 * permitir� escrev�-la usando o nome de nossos objetos, classes e atributos, e n�o mais por meio dos nomes de tabelas e colunas*/

		Conta conta = new Conta();
		conta.setId(2L);
		
		/* Para dizermos � JPA que a "conta" � um par�metro, bastar� inserirmos ":" na frente de seu nome e transformamos a primeira letra em mai�scula. Por
		 * conven��o, a comunidade usu�ria de JPA costuma adicionar tamb�m um "p" entre ":" e "conta" nessa mesma linha, indicando mais claramente de que se
		 * trata de um par�metro, substituindo o nome "conta" por "pConta" em "setParameter().*/
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		List<Movimentacao> resultList = query.getResultList();
		
		for (Movimentacao movimentacao : resultList) {
			System.out.println("Descri��o: " + movimentacao.getDescricao());
			System.out.println("Data.....: " + movimentacao.getData());
			System.out.println("Tipo Mov.: " + movimentacao.getTipoMovimentacao());
			System.out.println("Valor....: R$ " + movimentacao.getValor());
			System.out.println("Cliente..: " + movimentacao.getConta().getTitular());
			System.out.println("----------------------------------------------//-------------------------------");
		}

	}

}
