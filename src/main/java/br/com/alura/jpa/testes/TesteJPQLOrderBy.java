package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;

public class TesteJPQLOrderBy {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Conta conta = new Conta();
		conta.setId(2L);
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta";
		
		/* Na linha da lista, o "getResultList()" apresenta um warning do compilador indicando que um casting feito entre "list<>" e "Movimentação" não é checado;
		 * afinal, só é possível saber o tipo e conteúdo da lista quando executarmos o código.
		 * 
		 * Para evitar esse erro, é comum colocarmos o tipo de "query" mais específica chamada "TypedQuery", a qual é geralmente tipada com o que foi trazido
		 * na "query", e neste caso é a "Movimentacao"
		 * 
		 * Portanto, todas as operações que fizermos já serão vistas como movimentações; então passaremos o "class" da "Movimentacao" em "createQuery() para
		 * retirar o aviso do sistema de "getResultlist()"*/
		
		// Query query = em.createQuery(jpql)
		TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
		query.setParameter("pConta", conta);
		List<Movimentacao> resultList = query.getResultList();
		
		for (Movimentacao movimentacao : resultList) {
			System.out.println("");
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Data.....: " + movimentacao.getData());
			System.out.println("Tipo Mov.: " + movimentacao.getTipoMovimentacao());
			System.out.println("Valor....: R$ " + movimentacao.getValor());
			System.out.println("Cliente..: " + movimentacao.getConta().getTitular());
			System.out.println("----------------------------------------------//-------------------------------");
		}

	}

}
